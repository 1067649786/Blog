package com.ygy.newsmanagement.service;

import com.ygy.newsmanagement.entity.NewsComment;
import com.ygy.newsmanagement.util.PageQueryUtil;
import com.ygy.newsmanagement.util.PageResult;

public interface CommentService {
    /**
     * 添加评论
     *
     * @param newsComment
     * @return
     */
    Boolean addComment(NewsComment newsComment);

    /**
     * 后台管理系统中评论分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getCommentsPage(PageQueryUtil pageUtil);

    /**
     * 批量审核
     *
     * @param ids
     * @return
     */
    Boolean checkDone(Integer[] ids);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);
}
