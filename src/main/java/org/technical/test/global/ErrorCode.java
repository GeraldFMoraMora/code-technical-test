package org.technical.test.global;

public class ErrorCode {
    public static int ERROR_100 = 100;
    public static String ERROR_100_DESC = "We have not found that username";
    public static String ERROR_100_MSG = "ERROR: Username not exist";

    public static int ERROR_101 = 101;
    public static String ERROR_101_DESC = "That user not have keys";
    public static String ERROR_101_MSG = "ERROR: keys does not exits";

    public static int ERROR_102 = 102;
    public static String ERROR_102_DESC = "User entered an incorrect password";
    public static String ERROR_102_MSG = "ERROR: Password incorrect";

    public static int ERROR_103 = 103;
    public static String ERROR_103_DESC = "Duplicated username";
    public static String ERROR_103_MSG = "ERROR: Username not avaible";

    public static int ERROR_104 = 104;
    public static String ERROR_104_DESC = "User entered an exist and active task";
    public static String ERROR_104_MSG = "ERROR: Task Already exist and is active";

    public static int ERROR_105 = 105;
    public static String ERROR_105_DESC = "User entered info for an unknow task";
    public static String ERROR_105_MSG = "ERROR: Task not exist";

    public static int ERROR_106 = 106;
    public static String ERROR_106_DESC = "User entered an invalid token";
    public static String ERROR_106_MSG = "ERROR: Invalid Token";

}
