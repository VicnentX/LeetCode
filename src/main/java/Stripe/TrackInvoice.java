package Stripe;

import java.util.*;

public class TrackInvoice {
    private static final String USD_KEYWORD = "USD";
    private static final String CREATE_KEYWORD = "CREATE";
    private static final String FINALIZE_KEYWORD = "FINALIZE";
    private static final String PAY_KEYWORD = "PAY";
    private static final String ID_KEYWORD = "id";
    private static final String AMOUNT_KEYWORD = "amount";
    private static final String CURRENCY_KEYWORD = "currency";
    private static final Map<String , Integer> processOrder = new HashMap<String , Integer>() {{
        put(CREATE_KEYWORD, 1);
        put(FINALIZE_KEYWORD, 2);
        put(PAY_KEYWORD, 3);
    }};

    private static Map<String , UserInfo> userInfoDataBase;


    public static int CountOweInUSD(List<String> actions) {
        userInfoDataBase = new HashMap<>();
        int oweAmount = 0;

        for(String action : actions) {
            UserInfo userInfo = new UserInfo(action);

            if (userInfo.id == null) continue;

            if (!userInfoDataBase.containsKey(userInfo.id)
                    && userInfo.state != null
                    && userInfo.state.equals(CREATE_KEYWORD)
                    && userInfo.amount != null
                    && Integer.valueOf(userInfo.amount) > 0
                    && userInfo.currency.equals(USD_KEYWORD)) {
                userInfoDataBase.put(userInfo.id, userInfo);
            } else if(userInfoDataBase.containsKey(userInfo.id)
                    && userInfo.currency != null
                    && userInfo.currency.equals(USD_KEYWORD)
                    && userInfo.amount != null
                    && Integer.valueOf(userInfo.amount) > 0
                    && userInfo.state != null
                    && processOrder.get(userInfo.state) == 1 + processOrder.get(userInfoDataBase.get(userInfo.id).state)) {
                    userInfoDataBase.get(userInfo.id).state = userInfo.state;
                    userInfoDataBase.get(userInfo.id).amount = userInfo.amount;
            } else if (userInfoDataBase.containsKey(userInfo.id)
                    && userInfo.state != null
                    && userInfo.state.equals(PAY_KEYWORD)) {
                userInfoDataBase.get(userInfo.id).state = userInfo.state;
            }
        }

        for (String userId : userInfoDataBase.keySet()) {
            if (!userInfoDataBase.get(userId).state.equals(PAY_KEYWORD)) {
                oweAmount += Integer.valueOf(userInfoDataBase.get(userId).amount);
            }
        }
        return oweAmount;
    }

    //if there is extra attribute, i regard it as valid input
    private static class UserInfo {
        private String id = null, amount = null, currency = null, state;
        public UserInfo(String action) {
            String[] actionAndInfo = action.split(" ");
            this.state = actionAndInfo[0].split(":")[0].trim();
            String[] userInfoDetail = actionAndInfo[1].split("&");
            for (String token : userInfoDetail) {
                String[] pair = token.split("=");
                String key = pair[0].trim();
                String value = pair[1].trim();
                    switch (key) {
                        case ID_KEYWORD:
                            this.id = value;
                            break;
                        case AMOUNT_KEYWORD:
                            if(Integer.valueOf(value) > 0) {
                                this.amount = value;
                            }
                            break;
                        case CURRENCY_KEYWORD:
                            this.currency = value;
                            break;
                        default:
                            break;
                    }
            }
        }
    }

    public static void main(String[] args) {
        String a = "CREATE: currency=USD&id=1&amount=11";
        String b = "CREATE: currency=USD&id=1&amount=11&extra_field=sth";
        List<String> actions = new ArrayList<>();
        actions.add(a);
        actions.add(b);
        System.out.println(CountOweInUSD(actions));
        userInfoDataBase.clear();

        String c = "CREATE: id=13&amount=800&currency=USD";
        List<String> actions2 = new ArrayList<>();
        actions2.add(c);
        System.out.println(CountOweInUSD(actions2));
        userInfoDataBase.clear();

        String d = "CREATE: id=16&amount=800&currency=USD";
        String e = "FINALIZE: id=16&amount=600&currency=USD";
        List<String> actions3 = new ArrayList<>();
        actions3.add(d);
        actions3.add(e);
        System.out.println(CountOweInUSD(actions3));
        userInfoDataBase.clear();

        String f = "CREATE: id=14&amount=800&currency=USD";
        String g = "FINALIZE: id=14&amount=800&currency=USD";
        String h = "PAY: id=14";
        List<String> actions4 = new ArrayList<>();
        actions4.add(f);
        actions4.add(g);
        actions4.add(h);
        System.out.println(CountOweInUSD(actions4));
        userInfoDataBase.clear();
    }
}
