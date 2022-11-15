package com.zqz.school.dao.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: StudentPhotoUploadResp
 * @Date: Created in 14:09 2022-11-15
 */
@Data
public class StudentPhotoUploadResp implements Serializable {
    private static final long serialVersionUID = 777406603285910185L;

    private String photo;
}
