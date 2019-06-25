import java.util.*;
import java.util.stream.Collectors;

class Commands {

    Map<String, List<Integer>> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    private Scanner in = new Scanner(System.in);
    private List<Integer> numbers = new ArrayList<>();
    private String[] params;
    private String str;

    /**
     * array initialization and list sorting with stream api
     */
    void initArray(List<Integer> number){
        map.put("X", number.stream().filter(n -> n % 3 == 0).collect(Collectors.toList()));
        map.put("S", number.stream().filter(n -> n % 7 == 0).collect(Collectors.toList()));
        map.put("M", number.stream().filter(n -> n % 21 == 0).collect(Collectors.toList()));
        list = number.stream().filter(num -> num % 21 != 0)
                .filter(n -> n % 7 != 0)
                .filter(n -> n % 3 != 0)
                .collect(Collectors.toList());
        System.out.println(Text.WELLDONE);
    }

    /**
     * show list
     */
    void print(Map<String, List<Integer>> map, String[] array){
        for (String str : array) {
            if (map.get(str) == null || map.get(str).isEmpty()) {
                System.out.println( str + Text.EMPTY);                // shows which list is empty
            } else {
                System.out.println(map.get(str));
            }
        }

    }

    /**
     * shows the presence of the values are not included in any list (true/false)
     */
    void anyMore(List<Integer> list){
        try {
            System.out.println(!list.isEmpty());
        } catch (NullPointerException e) {
            System.out.println("false");
        }

    }

    /**
     * delete lists (X, S or M)
     */
    void clear(Map<String, List<Integer>> map, String[] array){
        for (String str : array) {
            map.put(str, null);
        }
        System.out.println(Text.DELETED);
    }

    /**
     * merge, print and clear all lists
     */
    void merge(Map<String, List<Integer>> map, List<Integer> list){
        System.out.println(Text.MERGE);
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
        System.out.println();
        for (String str : map.keySet()) {
            map.put(str, null);
        }
        list.clear();
        System.out.println(Text.CLEAR);
    }

    void help(){
        System.out.println(Text.HELP);
    }

    /**
     *  input validations
     */
    List<Integer> numbersEnteredHelper() {
        System.out.println(Text.ENTER_NUMBERS);
        String str = in.nextLine();
        boolean isInputOk = false;
        while (!isInputOk) {
            if (str.matches("(\\d+\\D+)*\\d+")) {
                isInputOk = true;
            } else {
                System.out.println(Text.INVALID);
                str = in.nextLine();
            }
        }

        String[] num = str.split("\\D+");
        numbers.clear();
        for (String strNum : num){
            numbers.add(Integer.valueOf(strNum));
        }
        numbers.sort(Comparator.naturalOrder());
        return numbers;
    }

    /**
     * X S M params for print and clear commands
     */
    String[] paramsXSM(){
        str = str.trim();
        if (str.contains(" ")) {
            boolean isParamsOk = false;
            while (!isParamsOk) {

                String commandParameters;
                try {
                    commandParameters = str.substring(str.indexOf(" ")).trim();
                } catch (StringIndexOutOfBoundsException e){
                    commandParameters = "X S M";
                }

                if (commandParameters.matches("([MXS]\\s*)+")) {
                    params = commandParameters.split(" ");
                    isParamsOk = true;
                } else {
                    System.out.println(Text.ENTER_PARAMETERS);
                    isParamsOk = false;
                    str = in.nextLine();
                }
            }
        } else {
            params = new String[]{
                    "X", "S", "M"
            };
        }
        return params;
    }

    /**
     * menuCommand from the program
     */
    String menuCommand(){
        str = in.nextLine();
        String command;
        if (str.contains(" ") && !str.contains("init array")) {
            command = str.split(" ")[0];
        } else {
            command = str;
        }
        return command;
    }

}

