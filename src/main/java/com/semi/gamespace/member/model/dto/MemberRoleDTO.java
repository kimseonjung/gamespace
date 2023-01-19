package com.semi.gamespace.member.model.dto;

public class MemberRoleDTO {
    private int memberCode;
    private int autorityCode;
    private MemberAuthorityDTO autority;

    public MemberRoleDTO() {
    }

    public MemberRoleDTO(int memberCode, int autorityCode, MemberAuthorityDTO autority) {
        this.memberCode = memberCode;
        this.autorityCode = autorityCode;
        this.autority = autority;
    }

    public int getMemberCode() {
        return memberCode;
    }
    public int getAutorityCode() {
        return autorityCode;
    }
    public MemberAuthorityDTO getAutority() {
        return autority;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }
    public void setAutorityCode(int autorityCode) {
        this.autorityCode = autorityCode;
    }
    public void setAutority(MemberAuthorityDTO autority) {
        this.autority = autority;
    }

    @Override
    public String toString() {
        return "MemberRoleDTO{" +
                "memberCode=" + memberCode +
                ", autorityCode=" + autorityCode +
                ", autority=" + autority +
                '}';
    }
}
