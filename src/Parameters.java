/**
 * Created by yyawesome on 15/11/20.
 */
public class Parameters {

    public static String userpath =  "time_series_data/";

    public static String[] fileNames={
            //Number of train, test cases, length, classes
            "Adiac",                    // 390,391,176,37
            "Beef",                     // 30,30,470,5
            "BeetleFly",               // 40,40,512,2
            "BirdChicken",             // 40,40,512,2
            "ChlorineConcentration",    // 467,3840,166,3
            "Coffee",                   // 28,28,286,2
            "DiatomSizeReduction",      // 16,306,345,4
            "DP_Little",                // 400,645,250,3
            "DP_Middle",                // 400,645,250,3
            "DP_Thumb",                 // 400,645,250,3
            "ECGFiveDays",              // 23,861,136,2
            "FaceFour",                 // 24,88,350,4
            "Gun_Point",                 // 50,150,150,2
            "ItalyPowerDemand",         // 67,1029,24,2
            "Lighting7",                // 70,73,319,7
            "MedicalImages",            // 381,760,99,10
            "MoteStrain",               // 20,1252,84,2
            "MP_Little",                // 400,645,250,3
            "MP_Middle",                // 400,645,250,3
            "Otoliths",               // 64,64,512,2
            "PP_Little",                // 400,645,250,3
            "PP_Middle",                // 400,645,250,3
            "PP_Thumb",                 // 400,645,250,3
            "SonyAIBORobotSurface",     // 20,601,70,2
            "Symbols",                  // 25,995,398,6
            "Synthetic_control",         // 300,300,60,6
            "Trace",                    // 100,100,275,4
            "TwoLeadECG",               // 23,1139,82,2

    };

    public static double[][] para = {
            {104,13,2,1}, //Adiac *
            {200,12,0.5,10},  //Beef  *
            {96,24,0.5,10},   //BeetleFly *
            {80,10,0.5,10},   //BirdChicken *
            {96,12,5,1},   //ChlorineConcentration *
            {32,16,5,10},    //Coffee *
            {150,75,0.5,10},   //DiatomSizeReduction
            {120,15,2,1},     //DP_Little *
            {60,6,2,1},     //DP_Middle
            {120,15,2,1},     //DP_Thumb
            {40,20,5,10},    //ECGFiveDays *
            {80,10,1,10},  //FaceFour *
            {60,15,1,10},    //GunPoint *
            {24,24,1,10}, //ItalyPowerDemand *
            {319,40,0.5,10}, //Lighting7 *
            {95,5,0.3,1},   //MedicalImages *
            {80,5,1.2,10},  //MoteStrain *
            {120,15,1,1},  //MP_Little
            {100,12,1,1},  //MP_Middle
            {200,12,0.2,10},  //Otoliths
            {100,12,0.7,1}, //PP_Little *
            {110,25,0.7,1}, //PP_Middle *
            {120,15,0.7,1}, //PP_Thumb
            {25,25,1,10},   //SonyAIBORobotSurface *
            {100,25,0.07,10},  //Symbols *
            {60,7,0.5,10},  //SyntheticControl *
            {36,36,1,10},     //Trace *
            {20,20,1.2,10}  //TwoLeadECG *
    };
}
