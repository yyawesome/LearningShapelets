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
            // Maxlength, Minlength, beta, alpha, C
            {104,13,2,0.5,1}, //Adiac *
            {200,15,2,20,10},  //Beef  *
            {96,12,2,5,10},   //BeetleFly *
            {80,5,2,5,10},   //BirdChicken *
            {96,12,2,0.2,1},   //ChlorineConcentration *
            {32,16,2,1,10},    //Coffee *
            {150,20,1.3,1,10},   //DiatomSizeReduction
            {120,15,2,5,1},     //DP_Little *
            {60,6,2,0.5,1},     //DP_Middle
            {120,15,2,0.5,1},     //DP_Thumb
            {40,20,2,1,10},    //ECGFiveDays *
            {80,20,1.5,1,10},  //FaceFour *
            {60,15,2,1,10},    //GunPoint *
            {24,20,1.2,1,10}, //ItalyPowerDemand *
            {319,40,2,2,10}, //Lighting7 *
            {95,5,1.5,1,1},   //MedicalImages *
            {40,10,1.2,5,10},  //MoteStrain *
            {120,15,2,5,1},  //MP_Little
            {100,12,2,5,1},  //MP_Middle
            {180,30,1.2,5,10},  //Otoliths
            {100,12,2,2,1}, //PP_Little *
            {110,25,2,2,1}, //PP_Middle *
            {120,12,2,2,1}, //PP_Thumb
            {25,25,2,1,10},   //SonyAIBORobotSurface *
            {100,50,2,15,10},  //Symbols *
            {60,7,2,1,10},  //SyntheticControl *
            {36,36,2,1,10},     //Trace *
            {20,12,1.2,1,10}  //TwoLeadECG *
    };
}
