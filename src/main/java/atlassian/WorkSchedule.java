    package atlassian;

    import java.lang.reflect.Array;
    import java.util.*;

    public class WorkSchedule {
        List<String> ret = new ArrayList<>();

        public List<String> makeSchedule(int totalHour , int dayHour , String s){
            char[] ch = s.toCharArray();
            List<Integer> index = new ArrayList<>();
            for(int i = 0 ; i < 7 ; ++i){
                if(!Character.isDigit(ch[i])){
                    index.add(i);
                }else{
                    totalHour = totalHour - Character.getNumericValue(ch[i]);
                }
            }
            //now totalHour is the remain hour we can schedule
            helper(totalHour , dayHour , ch , index ,0 , index.size() , new ArrayList<Character>());

            return ret;
        }
        private void helper(int totalHour , int dayHour , char[] ch , List<Integer> index , int cnt , int targetCnt , ArrayList<Character> tem){
            if(cnt == targetCnt){
                if(totalHour == 0){
                    for(int i = 0 ; i < targetCnt ; ++i){
                        ch[index.get(i)] = tem.get(i);
                    }
                    ret.add(String.valueOf(ch));
                }
                return;
            }
            for(int i = 0 ; i <= Math.min(dayHour , totalHour) ; ++i){
                tem.add((char)(i + '0'));
                helper(totalHour - i , dayHour , ch , index , cnt + 1 , targetCnt , tem);
                tem.remove(tem.size() - 1);
            }
        }

        public static void main(String[] args){
            WorkSchedule ws = new WorkSchedule();
            List<String> result = ws.makeSchedule(24 , 4 , "08??840");
            for(String k : result){
                System.out.println(k);
            }
        }
    }
