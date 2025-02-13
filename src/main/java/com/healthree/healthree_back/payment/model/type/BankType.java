package com.healthree.healthree_back.payment.model.type;

public enum BankType {
    경남은행("39"), 광주은행("34"), 단위농협("12"), 부산은행("32"), 새마을금고("45"), 산림조합("64"), 신한은행("88"), 신협("48"), 씨티은행("27"),
    우리은행("20"), 우체국예금보험("71"), 저축은행중앙회("50"), 전북은행("37"), 제주은행("35"), 카카오뱅크("90"), 토스뱅크("92"), 하나은행("81"),
    홍콩상하이은행("54"), IBK기업은행("03"), KB국민은행("06"), DGB대구은행("31"), KDB산업은행("02"), NH농협은행("11"), SC제일은행("23"), Sh수협은행("07");

    private String code;

    BankType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BankType of(String code) {
        for (BankType bankType : BankType.values()) {
            if (bankType.getCode().equals(code)) {
                return bankType;
            }
        }
        return null;
    }
}
