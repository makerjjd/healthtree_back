package com.healthree.healthree_back.payment.model.entity;

public enum CardCompanyType {
    기업BC("3K"), 광주은행("46"), 롯데카드("71"), KDB산업은행("30"), BC카드("31"), 삼성카드("51"), 새마을금고("38"), 신한카드("41"), 신협("62"),
    씨티카드("36"), 우리BC카드("33"), 우리카드("W1"), 우체국예금보험("37"), 저축은행중앙회("39"), 전북은행("35"), 제주은행("42"), 카카오뱅크("15"), 케이뱅크("3A"),
    토스뱅크("24"), 하나카드("21"), 현대카드("61"), KB국민카드("11"), NH농협카드("91"), Sh수협은행("34"), 다이너스클럽("6D"), 마스터카드("4M"),
    유니온페이("3C"), 아메리칸익스프레스("7A"), JCB("4J"), VISA("4V");

    private String code;

    CardCompanyType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CardCompanyType of(String code) {
        for (CardCompanyType cartCompanyType : CardCompanyType.values()) {
            if (cartCompanyType.getCode().equals(code)) {
                return cartCompanyType;
            }
        }
        return null;
    }
}
