package com.hanafi.petcare.utils;

public class UrlMapping {

    public static final String UPLOAD_DIR = "src/main/resources/uploads/users/";
    public static final String DEFAULT_PHOTO = "src/main/resources/uploads/default/default-avatar.jpg";
    public static final String ROOT = "/";
    public static final String API = "/api/v1";

    //route users
    public static final String USERS = API + "/users";
    public static final String USER_ID = "/{userId}";
    public static final String USER_UPDATE = "/update" + USER_ID;
    public static final String USER_REGISTER = "/register";
    public static final String DELETE = USER_ID;

    //route appointments
    public static final String APPOINTMENTS = API + "/appointments";
    public static final String APPOINTMENT_ID = "/{appointmentId}";
    public static final String APPOINTMENT_NO = "/get-by-no/{appointmentNo}";
    public static final String PETS = API + "/pets";
    public static final String PET_ID = "/{petId}";

    //reviews
    public static final String REVIEWS = API+"/reviews";

    //route pet

}
