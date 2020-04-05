package com.bw.dentaldoor.pojo;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class GroupedCount<E> {

    private E groupId;
    private Long itemCount;

    public GroupedCount(E groupId, Long itemCount) {
        this.groupId = groupId;
        this.itemCount = itemCount;
    }

    public E getGroupId() {
        return groupId;
    }

    public void setGroupId(E groupId) {
        this.groupId = groupId;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }
}
