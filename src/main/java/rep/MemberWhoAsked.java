package rep;


import entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberWhoAsked {
    List<Member> members = new ArrayList<>();

    public Member getForId(String id){
        for (Member member : members) {
            if(member.getId().equals(id)){
                return member;
            }
        }
        return null;
    }

    public void add(Member member){
        members.add(member);
    }

    public void removeForId(String id){
        members.removeIf(member -> member.getId().equals(id));
    }
}
