public enum ActionUnit {
    AU01(1, 1),
    AU02(2, 2),
    AU04(4, 3),
    AU05(5, 4),
    AU06(6, 5),
    AU07(7, 6),
    AU09(9, 7),
    AU10(10, 8),
    AU12(12, 9),
    AU14(14, 10),
    AU15(15, 11),
    AU17(17, 12),
    AU20(20, 13),
    AU23(23, 14),
    AU25(25, 15),
    AU26(26, 16),
    AU45(45, 17);

    public int number;
    public int fractionalIndex;
    public int booleanIndex;

    ActionUnit(int number, int fractionalIndex){
        this.number = number;
        this.fractionalIndex = fractionalIndex;
        this.booleanIndex = fractionalIndex + 18;
    }

    public static ActionUnit getAU(int n){
        for(ActionUnit au : values()){
            if(au.number == n){
                return au;
            }
        }
        return null;
    }

}
