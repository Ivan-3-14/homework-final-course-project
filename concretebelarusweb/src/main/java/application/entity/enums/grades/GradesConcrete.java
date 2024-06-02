package application.entity.enums.grades;

public enum GradesConcrete {
    M150(150),
    M200(200),
    M250(250),
    M300(300),
    M350(350),
    M400(400),
    M450(450),
    M500(500);

    GradesConcrete(int valueGrade) {
    }

    public static GradesConcrete getGradeFromValue(int value) {
        GradesConcrete result = null;
        switch (value) {
            case 150: result = M150;
            break;
            case 200: result = M200;
            break;
            case 250: result = M250;
            break;
            case 300: result = M300;
            break;
            case 350: result = M350;
            break;
            case 400: result = M400;
            break;
            case 450: result = M450;
            break;
            case 500: result = M500;
            break;
            default:
        }
        return result;
    }

    public static String getStringGrade(String value) {
        String result = null;
        switch (value) {
            case "М150" : result = "M150";
                break;
            case "М200" : result = "M200";
                break;
            case "М250" : result = "M250";
                break;
            case "М300" : result = "M300";
                break;
            case "М350" : result = "M350";
                break;
            case "М400" : result = "M400";
                break;
            case "М450": result = "M450";
                break;
            case "М500": result = "M500";
                break;
            default:
        }
        return result;
    }
}
