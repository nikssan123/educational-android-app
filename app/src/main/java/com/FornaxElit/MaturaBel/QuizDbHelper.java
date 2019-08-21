package com.FornaxElit.MaturaBel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.FornaxElit.MaturaBel.Quiz_Contract.*;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.releaseMemory;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MaturiApp.db";
    private static final int DB_VERSION = 10;
    private static final String nameOfTable = "questionsForHristoBotev";

    private SQLiteDatabase db;

   /* public void nameOfTable(String nameOfTable){
        this.nameOfTable = nameOfTable;
    }*/

    public QuizDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String table0 = "CREATE TABLE " +
                "belQuestionsTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table0);
        fillQuestionTableForBelQuestions();

        final String sql = "CREATE TABLE " +
                nameOfTable + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(sql);
        fillQuestionTableForHristoBotev();

        //Create new tables for different Quizes and increment DB_VERSION
        //then create new fillTheQuestionTable and addQuestion methods as well as getAllQuestions for the new table

        final String table1 = "CREATE TABLE IF NOT EXISTS " +
                "ivanVazovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table1);
        fillQuestionTableForIvanVazov();

        final String table2 = "CREATE TABLE IF NOT EXISTS " +
                "alekoKonstantinovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table2);
        fillQuestionTableForAlekoKonstantinov();

        final String table3 = "CREATE TABLE IF NOT EXISTS " +
                "penchoSlaveikovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table3);
        fillQuestionTableForPenchoSlaveikov();

        final String table4 = "CREATE TABLE IF NOT EXISTS " +
                "peyoQvorovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table4);
        fillQuestionTableForPeyoQvorov();


        final String table5 = "CREATE TABLE IF NOT EXISTS " +
                "elinPelinTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table5);
        fillQuestionTableForElinPelin();


        final String table6 = "CREATE TABLE IF NOT EXISTS " +
                "dimchoDebelqnovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table6);
        fillQuestionTableForDimchoDebelqnov();

        final String table7 = "CREATE TABLE IF NOT EXISTS " +
                "hristoSmirnenskiTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table7);
        fillQuestionTableForHristoSmirnenski();


        final String table8 = "CREATE TABLE IF NOT EXISTS " +
                "geoMilevTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table8);
        fillQuestionTableForGeoMilev();



        final String table9 = "CREATE TABLE IF NOT EXISTS " +
                "atanasDalchevTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table9);
        fillQuestionTableForAtanasDalchev();


        final String table10 = "CREATE TABLE IF NOT EXISTS " +
                "elisavetaBagrqnaTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table10);
        fillQuestionTableForElisavetaBagrqna();

        final String table11 = "CREATE TABLE IF NOT EXISTS " +
                "yordanYovkovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table11);
        fillQuestionTableForYordanYovkov();

        final String table12 = "CREATE TABLE IF NOT EXISTS " +
                "nikolaVapcarovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table12);
        fillQuestionTableForNikolaVapcarov();


        final String table13 = "CREATE TABLE IF NOT EXISTS " +
                "dimityrDimovTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table13);
        fillQuestionTableForDimityrDimov();


        final String table14 = "CREATE TABLE IF NOT EXISTS " +
                "dimityrTalevTable" + "( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(table14);
        fillQuestionTableForDimityrTalev();

        releaseMemory();
       // elisavetaBagrqnaTable

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "belQuestionsTable");
        db.execSQL("DROP TABLE IF EXISTS " + nameOfTable);
        db.execSQL("DROP TABLE IF EXISTS " + "ivanVazovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "alekoKonstantinovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "penchoSlaveikovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "peyoQvorovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "elinPelinTable");
        db.execSQL("DROP TABLE IF EXISTS " + "dimchoDebelqnovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "hristoSmirnenskiTable");
        db.execSQL("DROP TABLE IF EXISTS " + "geoMilevTable");
        db.execSQL("DROP TABLE IF EXISTS " + "atanasDalchevTable");
        db.execSQL("DROP TABLE IF EXISTS " + "elisavetaBagrqnaTable");
        db.execSQL("DROP TABLE IF EXISTS " + "yordanYovkovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "nikolaVapcarovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "dimityrDimovTable");
        db.execSQL("DROP TABLE IF EXISTS " + "dimityrTalevTable");
        onCreate(db);

    }

    private void fillQuestionTableForBelQuestions(){
        //Pravopis 10 vyprosa - DONE
        Question q1p = new Question("В кой ред е допусната правописна грешка?", "вървящи, стреляли, запели", "утроявам, овиквам, укупирам", "ситуации, пороища, медийни", "дръвник, закърпвам, вдлъбнат", 2);
        addQuestion(q1p,"belQuestionsTable");
        Question q2p = new Question("В кой ред е допусната правописна грешка?", "водолаз, мираж, кардиолог", "искрен, братовчедка, разтвор", "тягостна, шестотин, прелест", "сбирка, сгъвам, сглобявам", 3);
        addQuestion(q2p,"belQuestionsTable");
        Question q3p = new Question("В кое изречение е допусната правописна грешка?", "Нуждаем се от специалисти, които да отговарят за техническата поддръжка на машините.", "В началото на летния сезон предстои да започнат снимките на най-новия български сериал.", "Учените предупредиха, че популацията на египетските лешояди намалява с 7% на година.", "При почистването на кошерите пчеларят задължително трябва да е с предпазно облекло.", 3);
        addQuestion(q3p,"belQuestionsTable");
        Question q4p = new Question("В кое изречение е допусната правописна грешка?", "Популярният актьор обяви, че е решил да сложи край на своята 50-годишна кариера в театъра и в киното.", "Изложбата на известния художник модернист беше открита миналата седмица в новата галерия.", "Целта на всеки електронен търговец е да привлече възможно най-много посетители на своята уеб-страница.", "В интервю боксьорът сподели, че винаги е бил възпитаван да се труди и да не чака успехите наготово.", 3);
        addQuestion(q4p,"belQuestionsTable");
        Question q5p = new Question("В кое изречение е допусната правописна грешка?", "Националното състезание по счетоводство ще се проведе в една от Професионалните гимназии в столицата.", "По време на посещението си в града всички гости ще се запознаят с особеностите на архитектурата на барока.", "Утре експертите ще представят публично обобщените резултати от проведеното оценяване по математика.", "През седмицата в Панагюрище тържествено ще бъде отбелязана годишнината от рождението на Райна Княгиня.", 1);
        addQuestion(q5p,"belQuestionsTable");
        Question q6p = new Question("В кой ред е допусната правописна грешка?", "Някои учени смятат, че идеите на Европейското Просвещение са пренесени от гръцките възрожденци.", "През пролетните месеци трудно се откриват свободни стаи в хотелите, разположени в Южна България.", "Един от емблематичните пълководци в световната история е Александър Македонски.", "И до днес продължават научните спорове около Дарвиновата теория за произхода на човека.",1);
        addQuestion(q6p,"belQuestionsTable");
        Question q7p = new Question("В кое от изреченията НЕ е допусната правописна грешка?", "Съгласно наредба в големите магазини за бяла и черна техника ще има специални отдели за подръжка и ремонт.", "Диалектите са езикови вариянти, които са разпространени на определена територия и се използват от хората, които я населяват.", "На последното световно първенство по вдигане на тежести се запознах със най- младия шампион на нашата страна.", "Миналата година той претърпя успешна операция на лакътя и се върна на работа, след като излезе от болницата. ", 4);
        addQuestion(q7p,"belQuestionsTable");
        Question q8p = new Question("В кое от изреченията НЕ е допусната правописна грешка?", "Днес мнозина смятат, че най-престижното световно признание е получаването на Нобелова награда.", "Характерно за архитектурата на Барока е смелото струпване на обеми и пъстри цветови ефекти.", "В медиите обявиха, че започва ремонт на големите булеварди в няколко Софийски квартала.", "Заседанието ще е в понеделник, а като член на научната комисия е поканен Доцент Иван Господинов. ", 1);
        addQuestion(q8p,"belQuestionsTable");
        Question q9p = new Question("В кой от редовете е допусната правописна грешка?", "мятам, подметка, наметна", "чехъл, министър, поискъл", "дейност, лейтенант, йерархия", "бръквам, вържа, закърпен", 2);
        addQuestion(q9p,"belQuestionsTable");
        Question q10p = new Question("В кой от редовете НЕ е допусната правописна грешка?", "игрив, деспод, биолог", "исписан, безкраен, ловка", "честно, злостна, вносна", "сбиване, гонидба, сбогом", 3);
        addQuestion(q10p,"belQuestionsTable");


        //Gramatika 10 vyrposa - DONE
        Question q1g = new Question("В кое изречение е допусната граматична грешка?", "През цялата година Рилският и Троянският манастири са сред най-посещаваните забележителности в страната.", "За да отговорим на голямото търсене, ще пуснем за продажба в дистрибуторската мрежа още билети за концерта.", "Пресичайки внимателно улицата на пешеходната пътека, ние си гарантираме безопасно преминаване.", "Имам намерение преди утрешното заседание да се срещна и да се запозная с всички нови участници в проекта.", 1);
        addQuestion(q1g,"belQuestionsTable");
        Question q2g = new Question("В кое изречение е допусната грешка при членуването?", "В сряда ще бъдат обявени имената на допуснатите и на недопуснатите до участие в конкурса кандидати.", "В жилищния комплекс, който е близо до парка, се намира новият и просторен апартамент на сестра ми.", "С изненада установихме, че високият млад господин в коридора е санитарния инспектор за нашия район.", "Съвсем скоро новият ми съквартирант ще бъде назначен на работа в Агенцията за социално подпомагане.", 3);
        addQuestion(q2g,"belQuestionsTable");
        Question q3g = new Question("В кое от изреченията НЕ е допусната граматична грешка?", "Движението по първокласните и второкласни пътища ще е сериозно затруднено.", "Слънчевата светлина е основният и решаващ фактор за фотосинтезата.", "Много подходящ за разходка в парка се оказа днешния пролетен следобед.", "Изявените спортисти ще бъдат наградени от Български олимпийски комитет.", 2);
        addQuestion(q3g,"belQuestionsTable");
        Question q4g = new Question("В кое от изреченията е допусната граматична грешка?", "Всички усилия бяха насочени към осигуряването на еднаква трудова заетост за градското и за селското население.", "С настоящото писмо искаме да Ви благодариме за проявения траен интерес към дейността на нашата фирма.", "Баща ми влетя в стаята, носейки положителния отговор от фирмата, в която кандидатствах за работа.", "Необходимо е детайлно да запознаем юристите с всички условия по договора и да го обсъдим с тях.", 2);
        addQuestion(q4g,"belQuestionsTable");
        Question q5g = new Question("В кое от изреченията НЕ е допусната граматична грешка?", "Малките и големи ученици вече влизаха в час, когато удари звънецът.", "Тихата и прохладна майска утрин изпълни всички с вяра и с надежда.", "идя колко е часа и устремено се запъти към мястото на уговорената среща.", "В Изпълнителна агенция по горите обявиха свободните работни места.", 2);
        addQuestion(q5g,"belQuestionsTable");
        Question q6g = new Question("В кое от изреченията НЕ е допусната граматична грешка?", "Вчера българският и чешкият премиери проведоха работна среща в Брюксел.", "Бихме могли да въведеме специфични изисквания към бъдещите служители.", "Проверявайки финансовия отчет, счетоводителят откри неизправни документи.", "В интервю зрителят не одобрява и протестира срещу насилието над животните.", 3);
        addQuestion(q6g,"belQuestionsTable");
        Question q7g = new Question("В кое от изреченията НЕ е допусната граматична грешка?", "Гласуваните поправки в новия закон ще засегнат пряко въздушния и сухопътен транспорт в страната.", "Убедително написаното мотивационно писмо на младия и амбициозен кандидат впечатли комисия.", "Още утре г-н Каменов, адвоката на братовчедка ми, ще те потърси, за да подготвиш необходимите документи.", "По Искърско дефиле, което е с дължина около 100 км, са разположени множество интересни обекти.", 2);
        addQuestion(q7g,"belQuestionsTable");
        Question q8g = new Question("В кое от изреченията е допусната граматична грешка?", "Състезателите бяха настанени на първия и на втория етаж на хотела.", "Тук можем да се наслаждаваме на спокойствието и на чистия въздух.", "Минавайки на път за работа през парка, едно куче внезапно ме нападна.", "Още от дете обичам да разглеждам и да чета интересни книги.", 3);
        addQuestion(q8g,"belQuestionsTable");
        Question q9g = new Question("В кое от изреченията е допусната граматична грешка?", "Сребърните и златните бижута ще бъдат изложени на тази витрина.", "Знаех, че упоритият и старателен младеж ще постигне добри резултати.", "Отговорността за всичко, което се случва на борда, носи капитана.", "От Столичната община обявиха, че ще следят за спазването на правилата.", 3);
        addQuestion(q9g,"belQuestionsTable");
        Question q10g = new Question("В кое от изреченията е допусната граматична грешка?", "Червените и жълтите квадратчета на ризата ми сякаш образуваха пъстра мозайка.", "Бурните и студени морски вълни свирепо се блъскаха в скалите край брега.", "Обяви пред всички, че иска той да е организаторът на събитието тази година.", "На първото заседание беше приет новият правилник на Министерски съвет.", 4);
        addQuestion(q10g,"belQuestionsTable");


        //Punktuaciq 10 vyprosa - DONE
        Question q1pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "Минавайки покрай двамата свои съседи, младежът нито ги поздрави, нито ги погледна.", "Мисля си, че нашите мечти стават реалност само, ако повярваме, че това е възможно.", "Писмото, което получих, беше изпратено от служителя, с когото разговарях вчера в офиса.", "Сутринта моят колега се обади, че е болен, и помоли да довърша справката, която беше започнал.", 2);
        addQuestion(q1pun,"belQuestionsTable");
        Question q2pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "Къдравите коси, слънчевият загар и веселата усмивка на девойката веднага привлякоха всички погледи.", "Изучавайки движението на планетите, маите създали изключително точни карти на звездното небе.", "Финалистите, получили най-голям брой точки покриха нормативите за участие в олимпиадата.", "Разбира се, тя щеше да намери отново някакво логично обяснение за своето огромно закъснение.", 3);
        addQuestion(q2pun,"belQuestionsTable");
        Question q3pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "Слънчевото лято, обширните плажове и обновените хотели привличат много български и чуждестранни туристи.", "Катедралата, строена в продължение на почти два века и сградата на общината са едни от най-красивите постройки в Антверпен.", "Едно от най-важните нововъведения, оказали влияние върху развитието на обществото от ХV век нататък, е книгопечатането.", "Установяването на фактите обаче изисква проучването на огромен масив документи от страна на разследващите органи. ", 2);
        addQuestion(q3pun,"belQuestionsTable");
        Question q4pun = new Question("В кое от изреченията НЕ е допусната пунктуационна грешка?", "Мъглата се вдигна, въздухът стана някак прозрачен и чист, и доброто ни настроение се върна", "Въпреки, че голяма част от живота му преминава в чужбина, той остава тясно свързан с родината си.", "Историята е полезна не защото четем в нея за миналото, а защото виждаме чрез нея бъдещето", "Спря се изненадан пред посивелия мъж, в когото разпозна приятел от гимназията и го поздрави. ", 3);
        addQuestion(q4pun,"belQuestionsTable");
        Question q5pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "В региона се намират залежи от никелови руди, фосфати, злато, въглища и мед.", "Тук ще намерите огромна колекция от филми, спечелили награди през годините.", "Рано сутринта хуквам по пустите улици на града, надбягвайки се с времето.", "Нашите любезни спонсори да кажем, ще финансират провеждането на анкетата.", 4);
        addQuestion(q5pun,"belQuestionsTable");
        Question q6pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "Тя се вцепени от ужас, отвори уста, но не успя да каже нищо.", "Той понечи да отвори вратата, бравата на която беше счупена.", "Учениците, без да изчакат своя учител се качиха в автобуса.", "Прибрах се вечерта вкъщи и забелязах, че чантата ми я няма.", 3);
        addQuestion(q6pun,"belQuestionsTable");
        Question q7pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "В гардероба в коридора откриха не само празни куфари, но и няколко кашона.", "Маргарита, неговата голяма и единствена любов, неочаквано замина за чужбина.", "Разхождайки се по тесните улици на притихналия град, тя се озова на площада.", "По всяка вероятност, той беше разбрал за предстоящото пътуване на сестра си.", 4);
        addQuestion(q7pun,"belQuestionsTable");
        Question q8pun = new Question("В кое от изреченията НЕ е допусната пунктуационна грешка?", "Карнавалните маски се изработват от картон, дърво, пластмаса и кожа, и могат да представят различни характери.", "Ясно е, че децата, които растат с домашни любимци, се учат в ранна възраст да проявяват отговорно отношение.", "За да се предпазят от студа, императорските пингвини се скупчват на едно място като се редуват кой да е в средата.", "Запознах се с родителите, които поканих на срещата и дълго си говорихме за оценките и за дисциплината на децата.", 2);
        addQuestion(q8pun,"belQuestionsTable");
        Question q9pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "Автобиографията, както и попълненият формуляр за участие в конкурса, са подадени в срок.", "Замислен за проблемите на децата си, възрастният човек се разхождаше самотен в пустия парк.", "Залата на летището, изпълнена със заминаващи пътници и с техни близки, жужеше като кошер.", "Дебютният филм на младия режисьор обаче не беше сред номинираните за наградата на фестивала.", 1);
        addQuestion(q9pun,"belQuestionsTable");
        Question q10pun = new Question("В кое от изреченията е допусната пунктуационна грешка?", "От пътуването си в Испания донесе много рисунки и скици и сега ги разглеждаше внимателно и с умиление.", "Беше позадрямал край камината, стоплен и отпуснат след дългия път и умората, която не усети, докато шофираше.", "Когато детето излезе от водата, по гладката му кожа се стичаха едри капки, които блестяха на слънцето.", "Баща му дойде в офиса, без да го предупреди и младежът се изненада много, когато чу гласа му в коридора.", 4);
        addQuestion(q10pun,"belQuestionsTable");

        //Leksika 5 vyrposa - DONE
        Question q1l = new Question("В кое от изреченията НЕ е допусната лексикална грешка?", "Тази модерна туристическа абстракция събира хиляди туристи през лятото.", "Няма нужда от реверанси и поклони, когато чувствата не са искрени.", "Без заверено копие от нотариалния акт имотът не може да бъде продаден.", " Знаете ли за какво точно става на въпрос, или само ни заблуждавате?", 3);
        addQuestion(q1l,"belQuestionsTable");
        Question q2l = new Question("В кое от изреченията е допусната лексикална грешка?", "За да има разбирателство в семейството, всички трябва да правят компромиси.", "Дядото влезе и поздрави дъщеря си, която пъргаво и чевръсто приготвяше обяда.", "Раболепното му поведение отблъскваше всички – и познати, и колеги, и приятели.", "Вчера постъпиха две нови молби за отпускане на допълнителна помощ за лечение.", 2);
        addQuestion(q2l,"belQuestionsTable");
        Question q3l = new Question("В кое от изреченията НЕ е допусната лексикална грешка?", "След връчване на наградата режисьорът произнесе благодарно слово.", "Обръщението към публиката бе написано прочувствено и трогателно.", "След завършването на колежа започна да работи като рехабилитатор.", "Катастрофата на магистралата се случи благодарение на заледения асфалт.", 3);
        addQuestion(q3l,"belQuestionsTable");
        Question q4l = new Question("В кое изречение НЕ е допусната лексикална грешка?", "Остроумието в сложни ситуации винаги е било неговата запазена мярка.", "Обичам да го слушам, защото винаги се изразява лаконично и кратко.", "Целта на добрите оратори е да въздействат с думите си на публиката.", "След преживения шок мъжът получи анамнеза и забрави цялото си минало.", 3);
        addQuestion(q4l,"belQuestionsTable");
        Question q5l = new Question("В кое от изреченията НЕ е допусната лексикална грешка?", "Спортистът внезапно реши да стимулира контузия пред останалите, за да не участва в последните тренировки. ", "В края на първия си тежък работен ден младата репортерка се чувстваше твърде уморена и изтощена от умора.", "В знак на почит към върховния бог жреците извършвали ритуали, по време на които принасяли в жертва домашни животни.", "След дълги колебания бях решил, че още в петък сутринта си отивам на Ямбол, а в неделя ще отскоча и до Бургас.", 3);
        addQuestion(q5l,"belQuestionsTable");

        //Bonus - frazeologizmi - DONE
        Question qBonus1 = new Question("Кое от посочените фразеологични словосъчетания е различно по смисъл от останалите?", "през куп за грош", "през девет баира", "пет за четири", "през пръсти", 2);
        addQuestion(qBonus1, "belQuestionsTable");
        Question qBonus2 = new Question("Кое фразеологично словосъчетание означава внимавам да не сгреша?", "опирам на камък", "обирам си крушите", "опичам си ума", "оставам в сянка", 3);
        addQuestion(qBonus2, "belQuestionsTable");
        Question qBonus3 = new Question("В кой ред фразеологичните словосъчетания са синоними?", "трепва ми сърцето – къса ми се сърцето", "вкарвам в пътя – слагам на мястото", "кривя си шапката – свалям шапка", "скубя си косите – вадя си очите ", 2);
        addQuestion(qBonus3, "belQuestionsTable");
        Question qBonus4 = new Question("В кой от редовете фразеологичните словосъчетания са антоними?", "държа и хляба, и ножа – отпускам си устата ", "държа в сянка – отпускам ръце", "държа изкъсо – отпускам юздите ", "държа юздите – отпускам колана ", 3);
        addQuestion(qBonus4, "belQuestionsTable");

    }

    private void fillQuestionTableForHristoBotev(){

        Question q1 = new Question("От кое стихотворение на Христо Ботев са стиховете? \n\"Кървавата да вдигна напивка,\n от коя и любов немее, \nпък тогава и сам ще запея\nщо любя и за що милея!...\"" , "\"Елегия\"", "\"Моята молитва\"", "\"До моето първо либе\"", "\"На прощаване в 1868г.\"", 3);
        addQuestion(q1, nameOfTable);
        Question q2 = new Question("В коя лирическа творба говори не лирическият аз, а лирическият говорител?", "\"Хаджи Димитър\"", "\"На прощаване в 1868г.\"", "\"Обесването на Васил Левски\"", "\"Към брата си\"", 1);
        addQuestion(q2, nameOfTable);
        Question q3 = new Question("Кое твърдение НЕ е вярно за стихотворението \"Борба\"?", "Творбата е астрофична (не е разделена на строфи)", "Светът е устроен несправедливо и почита \"тиранство и зло\".", "Бог ще избави хората от социалното зло, вдъхвайки им вяра.", "Борбата е път и средство за преодоляване на \"тиранство и зло\"", 3);
        addQuestion(q3, nameOfTable);
        Question q4 = new Question("Кое твърдение е вярно?", "Поемата \"На прощаване в 1868г.\" афишира говорението на лирическия говорител и няма изповеден характер.", "\"Моята молитва\" е баладична творба, чийто адресат на говорене е традиционният християнски Бог, разпознат в трите му \"лица\"", "\"До моето първо либе\" откоява влюбеността на лирическия субект в героичната смърт - безсмъртие.", "Баладата \"Хаджи Димитър\" поетически възпява не духовното безсмъртие, а единствено трагичната смърт на значещата героична личност.", 3);
        addQuestion(q4, nameOfTable);
        Question q5 = new Question("В коя от изброените творби адресат на говоренето на лирическия аз е народът?", "\"До моето първо либе\"", "\"Елегия\"", "\"Борба\"", "\"Към брата си\"", 2);
        addQuestion(q5, nameOfTable);
        Question q6 = new Question("Кое стихотворение откроява проблема за принудителното изгнаничество?","\"До моето първо либе\"" , "\"Хаджи Димитър\"", "\"Моята молитва\"", "\"На прощаване в 1868г.\"", 4);
        addQuestion(q6, nameOfTable);
        Question q7 = new Question("Кое твърдение е вярно за стихотворението \"Към брата си\"", "Лирическия аз визира не кръвен свой роднина, а брат по дух и съдба", "\"Глупци неразбрани\" не са българите, живеещи в робство, а османските владетели", "Усещането за неразбраност и безнадеждност е надмогнато във финала на творбата", "Текстът откроява омразата като градивен контрапункт на любовта към себе си", 1);
        addQuestion(q7, nameOfTable);
        Question q8 = new Question("За кое от посочените творби се отнася твърдението? \n\t Страданието на лирическия субект е продиктувано от усещането за глобална неспревадливост в устроеността на света. Социално, национално и универсално зло се сливат в едно. Единствената алтернатива на робството лирическият говорител вижда в борбата, метонимично разпознаваема в \"свинец\" и номинативно изречена в паратекста (заглавието) на стихотворението", "\"Елегия\"",  "\"Обесването на Васил Левски\"",  "\"Борба\"",  "\"До моето първо либе\"", 3 );
        addQuestion(q8, nameOfTable);
        Question q9 = new Question("Кое е вярното твърдение за \"Обесването на Васил Левски\"?", "Жанрово събира балада и ода.", "Адресат на говоренето в текста е родната майка.", "Родината е видяна като родна майка", "Страданието не е водещ мотив", 3);
        addQuestion(q9, nameOfTable);
        Question q10 = new Question("От коя творба е посоченият откъс? \n\"Подкрепи и мен ръката,\nта кога въстане робът,\nв редовете на борбата\nда си найда и аз гробът!\nНе оставя да изстине\nбуйно сърце на чужбина,\nи гласът ми да премине\nтихо като през пустиня!...\"",  "\"Моята молитва\"",  "\"Майце си\"",  "\"На прощаване\"",  "\"Борба\"", 1);
        addQuestion(q10, nameOfTable);
        Question q11 = new Question("На кой ред всички стихотворения са написани от Христо Ботев?", "\"Елегия\", \"Борба\", \"При Рилския манастир\"", "\"На прощаване\", \"Левски\", \"Хаджи Димитър\"", "\"Майце си\", \"Моята молитва\", \"Към брата си\"", "\"Обесването на Васил Левски\", \"Кочо\", \"Ний\"", 3);
        addQuestion(q11, nameOfTable);
        Question q12 = new Question("Коя от историческите личности е спомената в творба на Христо Ботев?", "Филип Тотю", "Жельо Войвода", "Панайот Хитов", "Стефан Караджа", 4);
        addQuestion(q12, nameOfTable);
        Question q13 = new Question("Коя от изброените творби е балада", "\"На прощаване\"", "\"Хаджи Димитър\"", "\"До моето първо либе\"", "\"Обесването на Васил Левски\"", 2);
        addQuestion(q13, nameOfTable);
        Question q14 = new Question("В кое от произведенията по експресивен начин е представена робската участ на българския народ?", "\"Елегия\"", "\"Към брата си\"", "\"Моята молитва\"", "\"Майце си\"", 1);
        addQuestion(q14, nameOfTable);
        Question q15 = new Question("Коя от творбите изобразява картината на революционната буря?", "\"Обесването на Васил Левски\"", "\"До моето първо либе\"", "\"Хаджи Димитър\"", "\"Елегия\"", 2);
        addQuestion(q15, nameOfTable);
        Question q16 = new Question("В кое от стихотворенията отсъства мотивът за погубената младост?", "\"Моята молитва\"", "\"Към брата си\"", "\"Майце си\"", "\"Борба\"", 1);
        addQuestion(q16, nameOfTable);
        Question q17 = new Question("Кое твърдение е вярно?", "Първото публикувано стихотворение на Христо Ботев е \"Към брата си\"", "\"На прощаване\" е създадено по повод организирането на Ботевата чета", "Бащата на Христо Ботев - Ботьо Петков, е бил учител на Иван Вазов", "Ботев издава две стихосбирки, едната от които - със Стефан Караджа",2);
        addQuestion(q17, nameOfTable);
        Question q18 = new Question("В коя от творбите лирическият Аз се обръща към родината?", "\"Борба\"", "\"Хаджи Димитър\"", "\"До моето първо либе\"", "\"Обесването на Васил Левски\"", 4);
        addQuestion(q18, nameOfTable);
        Question q19 = new Question("Кой от мотивите не е характерен за поезията на Ботев?", "смъртта в борбата", "безсмъртието на бореца за свобода", "страданието на народа", "любовта като спасение", 4);
        addQuestion(q19, nameOfTable);
        Question q20 = new Question("Коя опозиция е от стихотворението \"На прощаване\"?", "земя - небе", "дом - път", "човек - Бог", "ден - нощ", 2);
        addQuestion(q20, nameOfTable);


        // "\"\""
        //Question q1 = new Question();
        //addQuestion(q1, nameOfTable);
    }

    private void fillQuestionTableForIvanVazov(){
        Question q1 = new Question("От кое Вазово стихотворение са стиховете? \n\"И чупи се воля и дух под хомота\nна нужди, в дълбока нощ гасне живота:\nни луч от съзнанье под покрива нищи!\nЧовекът словесни паднал е до скота.\"", "\"Отечество любезно\"", "\"Елате ни вижте\"", "\"Българският език\"", "\"Линее нашто поколение\"", 2);
        addQuestion(q1, "ivanVazovTable");
        Question q2 = new Question("Посочете НЕвярното твърдение: Героят Бойчо Огнянов от романа \"Под игото\" е:", "верен на националния идеал българин", "ярка индивидуалност със силен характер", "егоцентричен авантюрист и самовлюбен човек", "романтичен герой, посветен на делето на свободата", 3);
        addQuestion(q2, "ivanVazovTable");
        Question q3 = new Question("В коя Вазова творба е представено робското безвремие, в което живеят българите, през призмата на битовото и сатиричното?", "\"Под игото\"", "\"Дядо Йоцо гледа\"", "\"Елате ни вижте\"", "\"Чичивци\"", 4);
        addQuestion(q3, "ivanVazovTable");
        Question q4 = new Question("Кое е вярното твърдение за романа \"Под игото\"?", "Копринарката и Селямсъза са персонажи от \"Под игото\", но заедно с това и герои от друга Вазова творба.", "Романът \"Под игото\" е не толкова исторически, колкото авантюристично-приключенски роман, подражаващ на романтизма", "Домът, училището, манастирът, градът в творбата са смислово натоварени единствено с негативен знак.", "Романовото повествование визира подготовката за Априлското въстание.", 4);
        addQuestion(q4, "ivanVazovTable");
        Question q5 = new Question("Каква е художествената функция на заглавието на повестта \"Чичовци\"?", "Да открои значещи роднински връзки в патриархалното българско общество.", "Сатирично и иронично да представи еснафското живеене на българите \"под иго\".", "Да изтъкне разпадането на родовия свят във време на безметежно живеене.", "Да представи метафорично значимо историческо събитие, в което участват българите.", 2);
        addQuestion(q5, "ivanVazovTable");
        Question q6 = new Question("Коя лирическа творба НЕ е от цикъла \"Епопея на забравените\"?", "\"Кочо\"", "\"Опълченците на Шипка\"", "\"Българският език\"", "\"Паисий\"", 3);
        addQuestion(q6, "ivanVazovTable");
        Question q7 = new Question("Коя от изброените творби на Иван Вазов НЕ е свързана с темата за робството и националното освобождение?", "\"Под игото\"", "\"При Рилския манастир\"", "\"Чичовци\"", "\"Епопея на забравените\"", 2);
        addQuestion(q7, "ivanVazovTable");
        Question q8 = new Question("В коя Вазова творба \"говоренето\" измества \"правенето\", като персонажите живеят в дребни битови ежби и кавги?", "\"Дядо Йоцо гледа\"", "\"Епопея на забравените\"", "\"Линее нашто поколение\"", "\"Чичовци\"", 4);
        addQuestion(q8, "ivanVazovTable");
        Question q9 = new Question("Тезата, че един народ под робство никога не се самоубива, а \"яде, пие и прави деца\" е изказана в:", "романа \"Под игото\"", "повестта \"Чичовци\"", "разказа \"Дядо Йоцо гледа\"", "цикъла \"Епопея на забравените\"", 1);
        addQuestion(q9, "ivanVazovTable");
        Question q10 = new Question("На кой ред всички произведение са написани от Вазов", "\"Отечество любезно\", \"Българският език\", \"Ралица\"", "\"Опълченците на Шипка\", \"Моята молитва\", \"Борба\"", "\"Линее нашто поколение\", \"Елате ни вижте\", \"Кочо\"", "\"Левски\", \"Паисий\", \"Ни лъж не дъхва над полени\"", 3);
        addQuestion(q10, "ivanVazovTable");
        Question q11 = new Question("На кой ред всички имена са на герои от творбата \"Под игото\"?", "Кириак Стефчов, господин Фратю, Иричек", "Кандов, чорбаджи Марко, доктор Соколов", "Бойчо Огнянов, Колчо Слепеца, Гочоолъ", "Рада, Варлаам Копринарката, хаджи Ахил", 2);
        addQuestion(q11, "ivanVazovTable");
        Question q12 = new Question("Коя от изброените творби НЕ е ода?", "\"Кочо\"", "\"Левски\"", "\"Опълченците на Шипка\"", "\"Линее нашто поколение\"", 4);
        addQuestion(q12, "ivanVazovTable");
        Question q13 = new Question("В кое произведение се преплитат идеализираният и реалният образ на \"българското\"?", "\"Дядо Йоцо гледа\"", "\"Елате ни вижте\"", "\"До моето първо либе\"", "\"При Рилският манастир\"", 2);
        addQuestion(q13, "ivanVazovTable");
        Question q14 = new Question("В коя творба е представен комчиен портрет на българското възрожденско общество?", "\"Чичовци\"", "\"Под игото\"", "\"Дядо Йоцо гледа\"", "\"Бай Ганьо\"", 1);
        addQuestion(q14, "ivanVazovTable");
        Question q15 = new Question("Кой от изброените творци възпявя в произведенията си образите на ярки личности от нашата история?", "Христо Ботев", "Иван Вазов", "Пенчо Славейков", "Алеко Константинов", 2);
        addQuestion(q15, "ivanVazovTable");
        Question q16 = new Question("В кои от посочените творби полемичният тон е съчетан с възторжена прослава?", "\"Обесването на Васил Левски\", \"Майце си\"", "\"При Рилският манастир\", \"Хаджи Димитър\"", "\"Българският език\", \"Опълченците на Шипка\"", "\"Линее нашто поколение\", \"Елате ни вижте\"", 4);
        addQuestion(q16, "ivanVazovTable");
        Question q17 = new Question("Каква композиционна роля изпълнява появата на Иван Краличът в дома на чорбаджи Марко?", "завръзка", "развръзка", "кулминация", "експозиция", 1);
        addQuestion(q17, "ivanVazovTable");
        Question q18 = new Question("Коя от посочените творби има епилог?", "\"При Рилският манастир\"", "\"Опълчнците на Шипка\"", "\"Чичовци\"", "\"Под игото\"", 3);
        addQuestion(q18, "ivanVazovTable");
        Question q19 = new Question("Заманов и Кандов са герои от:", "\"Чичовци\"", "\"Епопея на забравените\"", "\"Дядо Йоцо гледа\"", "\"Под игото\"", 4);
        addQuestion(q19, "ivanVazovTable");
        Question q20 = new Question("Коя от посочените творби не е елегия?", "\"Хаджи Димитър\"", "\"Към брата си\"", "\"Елате ни вижте\"", "\"Елегия\"", 1);
        addQuestion(q20, "ivanVazovTable");


        //"\"\""
        //Question q1 = new Question();
        //addQuestion(q1, "ivanVazovTable");

    }

    private void fillQuestionTableForAlekoKonstantinov(){
        Question q1 = new Question("От коя творба на Алеко Константинов е цитатът \n\"...Ура, да живее амнистията!... Ох, сладко винце! Сега усещам всичката му прелест, сега, когато смъкнаха от плещите ми адския товар... И така, нашите приятели теглиха черта на миналото. Браво! Значи всичко от миналото е предадено вече на забвените, Браво!...\"", "\"Бай Ганьо\" – \"Бай Ганьо пътува\"", "\"Разни хора, разни идеали\" – първа част", "\"Разни хора, разни идеали\" – втора част", "\"Бай Ганьо\" – \"Бай Ганьо прави избори\"", 3);
        addQuestion(q1, "alekoKonstantinovTable");
        Question q2 = new Question("Жанрът на книгата Бай Ганьо е:", "авантюристично-приключенски роман", "хумористичен роман с отворен финал", "хумостистичен сборник с разкази акекдоти", "жанрът не може да бъде определен еднозначно", 4);
        addQuestion(q2, "alekoKonstantinovTable");
        Question q3 = new Question("Творбата \"Разни хора, разни идеали\" е композиционно разделена на:", "два фейлетона", "четири фрагмента", "един епизод", "три части", 2);
        addQuestion(q3,"alekoKonstantinovTable");
        Question q4 = new Question("Кое е вярното твърдение?", "Помощник-регистраторът е персонаж от първата част на книгата \"Бай Ганьо\".", "Бай Неделкович прави избори с Бай Ганьо в България, като е довереното му лице.", "Гротеската е присъщ похват на изображение в първия фрагмент на фейлетона \"Разни хора, разни идеали\".", "Фейлетонният цикъл \"Разни хора, разни идеали\" отркоява добродетелността на българниа след Освобождението.", 2);
        addQuestion(q4,"alekoKonstantinovTable");
        Question q5 = new Question("Коя от посочените функции НЕ е присъща на разказвачите в книгата \"Бай Ганьо\"?", "Разказвачите изтъкват различието между българското и европейското", "Разказвачите коментират и оценяват действията и постъпките на Бай Ганьо", "Разказвачите осмиват надценяването на своето и подценяването на чуждото", "Разказвачите идеализират образите на българското и на европейското", 3);
        addQuestion(q5,"alekoKonstantinovTable");
        Question q6 = new Question("Каква е художествената функция на заглавието във фейлетонния цикъл \"Разни хора, разни идеали\" (Отбележете НЕВЯРНОТО ТВЪРДЕНИЕ)", "Назовава иронично проблематиката на творбата", "Подчертава липсата на идеали в едно общество", "Очертава идеята за един ценностно \"преобърнат\" свят", "Представя многообразието на културни модели", 4);
        addQuestion(q6,"alekoKonstantinovTable");
        Question q7 = new Question("Коя част на книгата \"Бай Ганьо\" е с кръгова композиция?", "първата", "втората", "и двете", "нито една", 4);
        addQuestion(q7,"alekoKonstantinovTable");
        Question q8 = new Question("Кой епизод от фейлетонния цикъл \"Разни хора, разни идеали\" разкрива циничната житейска философия на дегенерата, празнуващ спасението си от съда?", "разказът за помощник-регистратора", "епизодът за амнистирания политически престъпник", "историята на симуланта родолюбец, жалещ за Македония", "монооголът на \"моралния\" чичо към племенника му", 2);
        addQuestion(q8,"alekoKonstantinovTable");
        Question q9 = new Question("Кой жанр е типичен за творчеството на Алеко Константинов?", "роман", "фейлетон", "къс разказ", "повест", 2);
        addQuestion(q9, "alekoKonstantinovTable");
        Question q10 = new Question("На кой ред и двете творби са написани от Алеко Константинов?", "\"Моята молитва\", \"Отечество любезно\"", "\"Дядо Йоцо Гледа\", \"До Чикаго и назад\"", "\"Чичовци\", \"Самотен гроб в самотен кът\"", "\"Разни хора, разни идеали\", \"Бай Ганьо\"", 4);
        addQuestion(q10,"alekoKonstantinovTable");
        Question q11 = new Question("На кой ред всички герои са на Алеко Константинов?", "лъжепатриотът, Гочоолу, Марийка, Михалаки Алафрангата", "помощник-регистратора, Гуньо, Дочоолу, Данко Харсъзина", "Бай Ганьо, Мичо Бейзадето, Иван Селямсъза, игуменът Натанаил", "кокона Поликсени, хаджи Ровоама, майката на Иречек, Мунчо", 2);
        addQuestion(q11,"alekoKonstantinovTable");
        Question q12 = new Question("\"Разни хора, разни идеали\" е цикъл от:", "фейлетони", "пътеписи", "разкази", "новели", 1);
        addQuestion(q12,"alekoKonstantinovTable");
        Question q13 = new Question("Кой от изброените герои е нравствен антипод на своя създател (Алеко Константинов)?", "Никола Търновалията", "Иваница Граматиков", "Ганьо Балкански", "Марк Аврелий", 3);
        addQuestion(q13,"alekoKonstantinovTable");
        Question q14 = new Question("В третата част на \"Разни хора, разни идеали\" в сатиричен план е разгледана темата за:", "користолюбието, алчността и раболепието на лъжепатриота", "кариеризма, подлостта и нищозните амбиции на чиновника", "безскрупността на амнистирания политически престъпник", "мъдрата \"философия\" и съвета на чичото на младия идеалист", 1);
        addQuestion(q14,"alekoKonstantinovTable");
        Question q15 = new Question("В кой откъс на книгата \"Бай Ганьо\" действието се развива в Прага?", "\"Бай Ганьо пътува\"", "\"Бай Ганьо на гости\"", "\"Бай Ганьо в банята\"", "\"Бай Ганьо в операта\"", 4);
        addQuestion(q15,"alekoKonstantinovTable");
        Question q16 = new Question("В кои от посочените произведения водещ е мотивът за робското примирение?", "\"Елегия\" и \"Към брата си\"", "\"Левски\" и \"На прощаване\"", "\"Бай Ганьо\" и \"Под игото\"", "\"Майце си\" и \"Чичовци\"", 1);
        addQuestion(q16,"alekoKonstantinovTable");
        Question q17 = new Question("Книгата \"Бай Ганьо\" е съставена от:", "разкази само на един повествовател", "разкази на самия герой в първо лице", "анекдоти за няколко отделни герои", "разкази на различни повествователи", 4);
        addQuestion(q17,"alekoKonstantinovTable");
        Question q18 = new Question("Какво е изразните средство в кавичките: \n\"Търговийка, предприятийца\" процеси имам в съдилищата - не може.", "алегория", "литота", "символ", "гротеска", 3);
        addQuestion(q18,"alekoKonstantinovTable");
        Question q19 = new Question("В кое произведение действието се развива в село в Искърското дефили?", "\"Чичовци\"", "\"Бай Ганьо\"", "\"Дядо Йоцо гледа\"", "\"На прощаване\"", 3);
        addQuestion(q19,"alekoKonstantinovTable");
        Question q20 = new Question("Кое от твърденията НЕ е вярно?", "Стихотворението \"Българският език\" изгражда и образа на защитника на родното слово", "\"Бай Ганьо\" е творба, разкриваща силата на българския дух и запазените патриархални традиции", "В Ботевите стихотворения се откриват библейски образи и мотиви и реалистични картини на страданието", "В разказа \"Дядо Йоцо гледа\" миналото и настоящето се срещат чрез съдбата на един сляп старец", 2);
        addQuestion(q20,"alekoKonstantinovTable");
    }

    private void fillQuestionTableForPenchoSlaveikov(){
        Question q1 = new Question("От кое стихотворение на Пенчо Славейков са стиховете? \n\"Не! Не! Живее всемогъщий дух - \nа с него аз в изкуството живея...\nИ загубата на единий слух\nнелесно тъй убива идеалът,\nкогато него Висший Слух поддържа!\"", "\"Ралица\"", "\"Ни лъх не дъхва над полени...\"", "\"Спи езерото\"", "\"Cis moll\"", 4);
        addQuestion(q1,"penchoSlaveikovTable");
        Question q2 = new Question("На кое литературно направление е представител Пенчо Славейков?", "Романтизъм", "Символизъм", "Модернизъм", "Постмодернизъм", 3);
        addQuestion(q2,"penchoSlaveikovTable");
        Question q3 = new Question("В коя творба на Пенчо Славейков се открива присъствието на фолклорната традиция?", "\"Спи езерото\"", "\"Самотен гроб\"", "\"Ралица\"", "\"Cis moll\"", 3);
        addQuestion(q3,"penchoSlaveikovTable");
        Question q4 = new Question("В коя творба на Пенчо Славейков творческата гениалност, белязана от лично страдание, е знак за богоизбраност и служене на човечеството?", "\"Ралица\"", "\"Ни лъх не дъхва над полени\"", "\"Cis moll\"", "\"Спи езерото\"", 3);
        addQuestion(q4,"penchoSlaveikovTable");
        Question q5 = new Question("Кое е вярното твърдение за поемата \"Ралица\"?", "Любовта стои над всичко, тя надмогва страданието и надживява тленността; любовта е вечна", "Стоичко Влаха обича Ралица и дарява младата жена с щастие и радост", "В поемата не се \"срещат\" фолклор и модерна естетика, а се демонстрира носталгия по народната песен", "Ралица силно и искрено обича детето си, но бързо забравя Иво след трагичната му смърт", 1);
        addQuestion(q5,"penchoSlaveikovTable");
        Question q6 = new Question("Кое твърдение НЕ е вярно за посочените творби на Пенчо Славейков?", "В \"Ни лъх не дъхва над полени\" природата е видяна през погледа на съзерцателя естет, като човекът не е случаен пътник в живота, а негов център", "В \"Спи езерото\" символ на вечната природа е езерната повърхност, в която се \"оглежда\" човешкия живот - трепване във всемира", "Поемата \"Ралица\" стои по поетически изказ далеч от фолклора, но по идейни внушения се сродява с него", "Поемата \"Cis moll\" споделя идеата, че творчеството на гения избраник е едновременно проклятие и благословия, като ощетеността е знак за одареност от висок порядък", 4);
        addQuestion(q6,"penchoSlaveikovTable");
        Question q7 = new Question("\"Извоюването на човека у българина\" според Пенчо Славейков означава: ", "липса на национална идентичност", "българското е свръхценност", "индивидуалността на човека е над нациолналността", "личността следва да се отрече от себе си", 3);
        addQuestion(q7,"penchoSlaveikovTable");
        Question q8 = new Question("Коя от изброените творби на Пенчо Славейков е поема?", "\"Cis moll\"", "\"Спи езерото\"", "\"Самотен гроб\"", "\"Ни дъх не лъхва...\"", 1);
        addQuestion(q8,"penchoSlaveikovTable");
        Question q9 = new Question("На кой ред всички стихотворения са написани от Пенчо Славейков?", "\"Хаджи Димитър\", \"Елате ни вижте\", \"Към брата си\"", "\"Cis moll\", \"Спи езерото\", \"Ралица\"", "\"Самотен гроб в самотен кът\", \"Елегия\", \"Майце си\"", "\"Ни лъх не дъхва...\", \"Паисий\", \"Градушка\"", 2);
        addQuestion(q9,"penchoSlaveikovTable");
        Question q10 = new Question("Къде са дадени имена на герои от творба на Пенчо Славейков?", "Иван Селямсъзат и хаджи Смион", "Иван Боримечката и поп Ставри", "Иво Бойкин и Стоичко Влаха", "Иваницовият син и Стойчо", 3);
        addQuestion(q10,"penchoSlaveikovTable");
        Question q11 = new Question("Коя от изброените творби НЕ е лирическа миниатюра?", "\"Спи езерото\"", "\"Ни лъх не дъхва...\"", "\"Самотен гроб в самотен кът\"", "\"Ралица\"", 4);
        addQuestion(q11,"penchoSlaveikovTable");
        Question q12 = new Question("Кое от посочените произведения внушава идеята, че чрез изкуството може да се надмогне страданието?", "\"Cis moll\"", "\"Елате ни вижте\"", "\"Отечество любезно\"", "\"Разни хора, разни идеали\"", 1);
        addQuestion(q12,"penchoSlaveikovTable");
        Question q13 = new Question("Коя от следните творби НЕ е поема?", "\"Опълченците на Шипка\"", "\"На прощаване\"", "\"Ралица\"", "\"Cis moll\"", 1);
        addQuestion(q13,"penchoSlaveikovTable");
        Question q14 = new Question("Мотивите за силата на духа и духовното усъвършенстване са характерни за творчеството на: ", "Иван Вазов", "Алеко Константинов", "Пенчо Славейков", "Христо Ботев", 3);
        addQuestion(q14,"penchoSlaveikovTable");
        Question q15 = new Question("В коя от посочените творби се откриват образи и мотиви, характерни за индивидуализма?", "\"Борба\"", "\"Ралица\"", "\"Разни хора, разни идеали\"", "\"При Рилския манастир\"", 2);
        addQuestion(q15,"penchoSlaveikovTable");
        Question q16 = new Question("От кое стихотворение е откъса: \n\"В зори ранил на път, аз дишам\nна лятно утро свежестта - \nи милва ми душата бодра\nза лек път охолна мечта.\"", "\"Спи езерото\"", "\"Ни лъх не дъхва над полени\"", "\"До моето първо либе\"", "\"При Рилския манастир\"", 2);
        addQuestion(q16,"penchoSlaveikovTable");
        Question q17 = new Question("Епилогът е:", "изречение или цитат, поставен пред текста на цялостно литературно произведение или негова част", "забавяне на действието, за да се засили вниманието на читателя и да се повиши напрежението", "завършваща, извънсюжетна част, отделена от развръзката с известен период от време, в която се разкрива по-нататъшна съдба на героите", "моментът, в който се очертава основният конфликт чрез конкретна случка или действие на героите", 3);
        addQuestion(q17,"penchoSlaveikovTable");
        Question q18 = new Question("Кое от посочените твърдения НЕ е вярно?", "Христо Ботев получава световно признание заради своите безсмъртни оди", "Иван Вазов създава ненадминати образци в различни литературни жанрове", "Алеко Константинов утвърждава фейлетона като жанр в нашата литература", "Лирическите миниатюри са характерни за творчеството на Пенчо Славейков", 1);
        addQuestion(q18,"penchoSlaveikovTable");
        Question q19 = new Question("Коя от изброените творби разглежда темата за правото на човека да направи свободен избор и да отстоява моралните си ценности?", "\"Майце си\"", "\"Ралица\"", "\"При Рилския манастир\"", "\"Ни лъх не дъхва над полени\"", 2);
        addQuestion(q19,"penchoSlaveikovTable");
        Question q20 = new Question("Какво е изразното средство в скобите? \n(Ни лъх не дъхва над полени, \nни трепва лист от дървеса,)\nогледва ведър лик небото\nв море от бисерна роса.", "анафора", "анжамбман", "синтактичен паралелизъм", "епифора", 1);
        addQuestion(q20,"penchoSlaveikovTable");
    }

    private void fillQuestionTableForPeyoQvorov(){
        Question q1 = new Question("От кое стихотворение са стиховете? \n\"Душата ми е стон. Душата ми е зов.\nЗащото аз съм птица устрелена:\nна смърт е моята душа ранена\nна смърт ранена от любов...\"", "\"Две хубави очи\"", "\"Стон\"", "\"Сенки\"", "\"Ще бъдеш в бяло\"", 2);
        addQuestion(q1,"peyoQvorovTable");
        Question q2 = new Question("Коя от посочените Яворови творби е поема?", "\"Ще бъдеш в бяло\"", "\"Градушка\"", "\"Маска\"", "\"Стон\"", 2);
        addQuestion(q2,"peyoQvorovTable");
        Question q3 = new Question("Коя опозиция е характерна за стихотворението на Пейо Яворов \"Маска\"?", "любов - омраза", "живот - смърт", " надежда - отчаяние", "родно - чуждо", 3);
        addQuestion(q3,"peyoQvorovTable");
        Question q4 = new Question("Сивчо и Ваньо са герои от творба на:", "Пенчо Славейков", "Пейо Яворов", "Иван Вазов", "Алеко Константинов", 2);
        addQuestion(q4,"peyoQvorovTable");
        Question q5 = new Question("Коя от посочените творби е символистична?", "\"Елегия\"", "\"Българският език\"", "\"Ралица\"", "\"Маска\"", 4);
        addQuestion(q5,"peyoQvorovTable");
        Question q6 = new Question("За кое от стихотворенията на Пейо Яворов е характерен мотивът за търсения смисъл на живота?", "\"Градушка\"", "\"Песента на човека\"", "\"Ще бъдеш в бяло\"", "\"Две хубави очи\"", 2);
        addQuestion(q6,"peyoQvorovTable");
        Question q7 = new Question("В коя от посочените творби НЕ присъства образът на любимата жена?", "\"На прощаване\"", "\"Ще бъдеш в бяло\"", "\"Линее нашто поколенье\"", "\"Ралица\"", 3);
        addQuestion(q7,"peyoQvorovTable");
        Question q8 = new Question("Кoе от посочените твърдения НЕ е вярно?", "Стихотворението \"Заточеници\" е написано в изповедно-монологична форма и има елегичен характер", "В стихотворението \"Две души\" е наложена идеята за хармонията в живота на човека", "\"Градушка\" е творба, изградена от шест части, обособени емоционално, смислово и композиционно", "В стихотворението \"Две хубави очи\" образът на жената е дематириализиран, идеализиран и безплътен", 2);
        addQuestion(q8,"peyoQvorovTable");
        Question q9 = new Question("Кое твърдение се отнася за творчеството на Пейо Яворов?", "Лирическият герой от поезията му съзерцава живота в отделните негови проявления", "Героят пътешества, но не успява да се докосне до същността на чуждия свят", "Азът е категоричен в избора на път в живота и настойчиво призовава другите да го последват", "Човекът е разкит в драматични ситуации на питане и очакване, изгаря в огъня на противоречията", 4);
        addQuestion(q9,"peyoQvorovTable");
        Question q10 = new Question("На кой ред всички стихотворения са написани от Пейо Яворов?", "\"Ще бъдеш в бяло\", \"Стон\", \"Две души\"", "\"Две хубави очи\", \"Майце си\", \"Сенки\"", "\"Градушка\", \"Заточеници\", \"Спи градът\"", "\"Песента на човека\", \"Cis moll\", \"Маска\"", 1);
        addQuestion(q10,"peyoQvorovTable");
        Question q11 = new Question("В кое от следните произведения се внушава идеята, че човекът е подвластен на природата?", "\"Самотен гроб в самотен кът\"", "\"Разни хора, разни идеали\"", "\"Отечество любезно\"", "\"Градушка\"", 4);
        addQuestion(q11,"peyoQvorovTable");
        Question q12 = new Question("Темата \"Прощаване с родното\" се отнася за стихотворението?", "\"Стон\"", "\"Маска\"", "\"Заточеници\"", "\"Ще бъдеш в бяло\"", 3);
        addQuestion(q12,"peyoQvorovTable");
        Question q13 = new Question("Коя от изброените Яворови творби представя образа на природата \"мащеха\" и разкрива прокълнатостта на човека в един свят на ирационални сили?", "\"Песента на човека\"", "\"Ще бъдеш в бяло\"", "\"Градушка\"", "\"Заточеници\"", 3);
        addQuestion(q13,"peyoQvorovTable");
        Question q14 = new Question("В коя от изброените Яворови творби антитезисно се противопоставят силата и безсилието, любовта към родината и гневът от принудителната раздяла с отечеството?", "\"Сенки\"", "\"Заточеници\"", "\"Песента на човека\"", "\"Маска\"", 2);
        addQuestion(q14,"peyoQvorovTable");
        Question q15 = new Question("В коя от следните творби образът на любимата е сравнен с небесно същество (серафим, ангел)?", "\"Сенки\"", "\"Ще бъдеш в бяло\"", "\"Две души\"", "\"Две хубави очи\"", 2);
        addQuestion(q15,"peyoQvorovTable");
        Question q16 = new Question("Стихотворението \"Две души\" е:", "оптимистичен поглед в бъдещето", "самоанализа на човешката същност", "свързано с мотива за несподелената любов", "пейзажно", 2);
        addQuestion(q16,"peyoQvorovTable");
        Question q17 = new Question("Посочете чувството, което НЕ е характерно за Яворовата поезия", "напрегнатост в отношението към живота", "усещане за самота в тълпата", "спокойствие и примирение, идващо от съхранената надежда", "емоционална поляризация на чувствата", 3);
        addQuestion(q17,"peyoQvorovTable");
        Question q18 = new Question("Определете мястото на Яворов в българската литература", "последен възрожденски поет", "представител на експресионизма", "представител на сантиментализма", "пръв \"модерен поет\"", 4);
        addQuestion(q18,"peyoQvorovTable");
        Question q19 = new Question("Какви мотиви преобладават в ранните Яворови творби?", "символистични", "социални", "индивидуалистични", "романтични", 2);
        addQuestion(q19,"peyoQvorovTable");
        Question q20 = new Question("Белият цвят в любовната лирика на Яворов НЕ означава:", "магнетична изкусителност", "нежност и невинност", "абсолютно съвършенство", "духовна извисеност", 1);
        addQuestion(q20, "peyoQvorovTable");
    }

    private void fillQuestionTableForElinPelin(){
        Question q1 = new Question("Кое от посочените заглавия е загалвие на цикъл разкази от Елин Пелин?", "\"Старопланински легенди\"", "\"Занемелите камбани\"", "\"Епопея на забравените\"", "\"Под манастирската лоза\"", 4);
        addQuestion(q1,"elinPelinTable");
        Question q2 = new Question("В кой от посочените разкази на Елин Пелин пътят отвежда в блатото - метафора на безизходността?", "\"Мечтатели\"", "\"Ветренната мелница\"", "\"Андрешко\"", "\"Косачи\"", 3);
        addQuestion(q2,"elinPelinTable");
        Question q3 = new Question("Кое е вярното твърдение?", "Разказът \"Косачи\" говори за безсмисления труд на селяка, който го лишава от мечти и надежди", "\"Ветренната мелница\" представя нещастната любов между двама млади, която е причина за неудовлетвореността от живота", "Праведното и грешното, доброто и злото са тема на разказа \"Чорба от греховете на отец Никодим\"", "\"На оня свят\" е разказ за трагичната смърт на млад човек, който загива от чужда ръка", 3);
        addQuestion(q3,"elinPelinTable");
        Question q4 = new Question("Кое от посочените твърдения се отнася за разказа \"Мечтатели\"?", "В разказа не се противопоставят две реалности - ежедневната и мечтаната, а се откроява страданието от непосилния селски труд", "Рационализираният свят не дава простор на фантазията, станал е чужд и неразбираем, но пък с това провокира мечтателността като алтернатива", "Животът събира хорските неволи, защото волите им са безсилни пред предизвикателствата на съдбата и споменът за мъртвите възкресява кротостта у живите", "\"Тоя\" и \"оня\" свят са смислово преобърнати, като ирочнично се откроява насадената представа за праведност, прикриваща манипулацията на църквата", 2);
        addQuestion(q4,"elinPelinTable");
        Question q5 = new Question("В коя от изброените творби смислово функционира фолклорната традиция на \"надиграването\"?", "\"Задушница\"", "\"Андрешко\"", "\"Ветрената мелница\"", "\"Мечтатели\"", 3);
        addQuestion(q5,"elinPelinTable");
        Question q6 = new Question("Коя от посочените творби преосмисля християнската етика в ироничен план?", "\"Косачи\"", "\"Занемелите камбани\"", "\"Мачтатели\"", "\"На оня свят\"", 4);
        addQuestion(q6,"elinPelinTable");
        Question q7 = new Question("Коя от посочените творби поставя проблема за рода и индивида?", "\"Косачи\"", "\"Мечтатели\"", "\"Гераците\"", "\"Задушница\"", 3);
        addQuestion(q7,"elinPelinTable");
        Question q8 = new Question("На кой ред всички творби са написани от Елин Пелин?", "\"Андрешко\", \"Задушница\", \"Заточеници\"", "\"Ветрената мелница\", \"Косачи\", \"Гераците\"", "\"Занемелите камбани\", \"Чичовци\", \"Ралица\"", "\"Мечтатели\", \"Тиха победа\", \"На оня свят\"", 2);
        addQuestion(q8,"elinPelinTable");
        Question q9 = new Question("Станчо и Стоилка са герои от:", "\"Задушница\"", "\"Мечтател\"", "\"Андрешко\"", "\"Косачи\"", 1);
        addQuestion(q9,"elinPelinTable");
        Question q10 = new Question("Коя от изброените творби е повест?", "\"Дядо Йоцо гледа\"", "\"Ветрената мелница\"", "\"Гераците\"", "\"Мечтатели\"", 3);
        addQuestion(q10,"elinPelinTable");
        Question q11 = new Question("В коя от посочените творби са разгледани темите за трудолюбието, виталността и любовта", "\"На оня свят\"", "\"Ветрената мелница\"", "\"Занемелите камбани\"", "\"Чорба от греховете на отец Никодим\"", 2);
        addQuestion(q11,"elinPelinTable");
        Question q12 = new Question("В кои от изброените произведения може да се открие социалната драма на българският селянин?", "\"Чичовци\" и \"Мечтатели\"", "\"Ралица\" и \"Заточеници\"", "\"Стон\" и \"Моята молитва\"", "\"Градушка\" и \"Андрешко\"", 4);
        addQuestion(q12,"elinPelinTable");
        Question q13 = new Question("В коя от посочените творби е представен разпадът на патриархалния свят под натиска на нов обществен ред?", "\"Под игото\"", "\"Бай Ганьо\"", "\"Гераците\"", "\"Ралица\"", 3);
        addQuestion(q13,"elinPelinTable");
        Question q14 = new Question("Кое от посочените твърдения НЕ е вярно за реализма като литературно направление?", "Реализмът създава нова творческа нагласа, независима от обществото", "Писателите реалисти разглеждат социални и нравствени проблеми", "При реализма действителността се отразява правдиво, вярно и пълно", "Реализмът  дава представа за типични герои при типични обстоятелства", 1);
        addQuestion(q14,"elinPelinTable");
        Question q15 = new Question("Функцията на развръзка в творбата \"На оня свят\" изпълнява епизодът, в който се представя:", "разказът на баба Йова за смъртта на дядо Матейко", "съгласието на героя да остане в рая, където няма бирници", "спорът на селянина със свети Петър къде е мястото му", "разговорът на стареца с ангелчето за попа и владиката", 2);
        addQuestion(q15,"elinPelinTable");
        Question q16 = new Question("Елка и Йовка са герои от творбата:", "\"Ветрената мелница\"", "\"Под игото\"", "\"Гераците\"", "\"Задушница\"", 3);
        addQuestion(q16,"elinPelinTable");
        Question q17 = new Question("В кои от разказите на Елин Пелин се поставя проблемът за истинските и формалните ценности в живота и за неумолимите закони на човешката природа?", "\"На оня свят\" и \"Задушница\"", "\"Занемелите камбани\" и \"Чорба от греховете на отец Никодим\"", "\"Андрешко\" и \"Ветрената мелница\"", "\"Мечтатели\" и \"Косачи\"", 2);
        addQuestion(q17,"elinPelinTable");
        Question q18 = new Question("От кое произведение е посоченият откъс: \n\"Дядо Йордан ги изпращаше до портата и като гледаше изток - ясен, побелял от предвестни зори, - говореше възхитено. \n- Ех, че време ли се отваря! Пак ще се къпе в човешки пот майката земя.\"", "\"Чичовци\"", "\"Гераците\"", "\"Под игото\"", "\"Бай Ганьо\"", 2);
        addQuestion(q18,"elinPelinTable");
        Question q19 = new Question("Коя опозиция липвсва в разказа \"Андрешко\"?", "държава - данъкоплатци", "човек - природа", "град - село", "състрадание - безсърдечност", 2);
        addQuestion(q19,"elinPelinTable");
        Question q20 = new Question("В сюжета на разказа \"Занемелите камбани\" моментът на първото чудо - онемяване на камбаните, е:", "експозиция", "завръзка", "кулминация", "развръзка", 3);
        addQuestion(q20,"elinPelinTable");
    }

    private void fillQuestionTableForDimchoDebelqnov(){
        Question q1 = new Question("Кое Дебелянова стихотворение е сонет?", "\"Помниш ли, помниш ли...\"", "\"Да се завърнеш в бащината къща\"", "\"Пловдив\"", "\"Един убит\"", 3);
        addQuestion(q1,"dimchoDebelqnovTable");
        Question q2 = new Question("В коя от изброените Дебелянови творби \"спомен, двор, затвор\" са ключови думи?", "\"Да се завърнеш в бащината къща\"", "\"Черна песен\"", "\"Помниш ли, помниш ли...\"", "\"Сиротна песен\"", 3);
        addQuestion(q2,"dimchoDebelqnovTable");
        Question q3 = new Question("Кое твърдение е вярно?", "Метафизическата безприютност на човека в света белязва трайно Дебеляновата лирика", "Дебеляновият лирически човек избира действителността пред бляна", "Дебеляновата лирика не интерпретира мотива за завръщането", "Пътят в Дебеляновата лирика не е обвързан с безпътицата \"тук\" и \"сега\"", 1);
        addQuestion(q3,"dimchoDebelqnovTable");
        Question q4 = new Question("Димчо Дебелянов е:", "поет реалист", "поет символист", "поет романтик", "поет експресионист", 2);
        addQuestion(q4,"dimchoDebelqnovTable");
        Question q5 = new Question("Кой от изброените мотиви НЕ е интерпретиран в стихотворението \"Един убит\"?", "Войната е част от човешкото битие и носи страдание", "Войната конфронтира хората чрез манипулация", "Войната е израз единствено на патриотизъм и справедливост", "Във войната няма победители и победени, има смърт", 3);
        addQuestion(q5,"dimchoDebelqnovTable");
        Question q6 = new Question("За какво говори сонетът \"Пловдив\"?", "За един красив и обичан от лирическия субект град", "За екзистенциалните корена на самотата", "За радостта и насладата от детството", "За умилението пред родния дом и родния град", 2);
        addQuestion(q6,"dimchoDebelqnovTable");
        Question q7 = new Question("Кои от посочените характеристики НЕ са присъщи на символизма?", "Трайно е обвързан с идеята за трансцендентното", "Присъщ му е красивият, поетичен, мелодичен език", "Светът \"тук\" и \"сега\" е противопоставен на света \"там\" и \"тогава\"", "Отхвърля меланхолията, отчуждението и елегичността", 4);
        addQuestion(q7,"dimchoDebelqnovTable");
        Question q8 = new Question("Стихът \"Светлий спомен за теб е кат книга любима\" съдържа:", "метафора и хипербола", "метафора и сравнение", "метафора и алегория", "метонимия и оксиморон", 2);
        addQuestion(q8,"dimchoDebelqnovTable");
        Question q9 = new Question("На кой ред всички стихотворения са написани от Димчо Дебелянов?", "\"Помниш ли, помниш ли...\", \"Миг\", \"Един убит\"", "\"Черна песен\", \"Две хубави очи\", \"Тиха победа\"", "\"Пловдив\", \"Сиротна песен\", \"Песента на човека\"", "\"Да се завърнеш в бащината къща\", \"Спи градът\", \"Да бъде ден!\"", 1);
        addQuestion(q9,"dimchoDebelqnovTable");
        Question q10 = new Question("В коя от изброените творби е представен образът на майката?", "\"Спи градът\"", "\"Ветрената мелница\"", "\"Да се завърнеш в бащината къща\"", "\"Черна песен\"", 3);
        addQuestion(q10,"dimchoDebelqnovTable");
        Question q11 = new Question("Коя от следните творби НЕ е елегия?", "\"Обесването на Васил Левски\"", "\"Ни лъх не дъхва над полени\"", "\"Помниш ли, помниш ли...\"", "\"Да се завърнеш в бащината къща\"", 2);
        addQuestion(q11,"dimchoDebelqnovTable");
        Question q12 = new Question("Мотивите за изграждането и разрушаването са от творбата на Димчо Дебелянов:", "\"Черна песен\"", "\"Сиротна песен\"", "\"Тиха победа\"", "\"Един убит\"", 1);
        addQuestion(q12,"dimchoDebelqnovTable");
        Question q13 = new Question("В творчеството на кого от изброените автори НЕ присъства образът на сиромаха?", "Елин Пелин", "Пенчо Славейков", "Христо Ботев", "Димчо Дебелянов", 4);
        addQuestion(q13,"dimchoDebelqnovTable");
        Question q14 = new Question("За композицията на стихотворението \"Черна песен\" е характерно:", "поставя акцент на индивида като личност", "градира чувствата от тъжно към радостно", "изградена е на принципа на антитезите", "използвана е ретардация в изображението", 3);
        addQuestion(q14,"dimchoDebelqnovTable");
        Question q15 = new Question("Кое от посочените произведения НЕ е част от модернизма в българската литература?", "\"Помниш ли, помниш ли...\"", "\"Маска\"", "\"Спи езерото\"", "\"Задушница\"", 4);
        addQuestion(q15,"dimchoDebelqnovTable");
        Question q16 = new Question("Кое от посочените твърдения НЕ е вярно за поезията на Димчо Дебелянов?", "Значима роля играе елегичното - като жанров определител и емоционална доминанта", "Раздвоената и драматична напрегнатост на чувствата намира израз в констрастна образност", "Има сериозно разминаване между житейската биография на поета и поетическата биография на героя", "Доминара усещането за самота, бездомност и меланхолия в широк екзистенциален план", 3);
        addQuestion(q16,"dimchoDebelqnovTable");
        Question q17 = new Question("В коя от посочените двойки произведения преобладава елегичното чувство, свързано с раздяла?", "\"Да се завърнеш в бащината къща\" и \"Заточеници\"", "\"Спи езерото\" и \"Тиха победа\"", "\"Песента на човека\" и \"Линее нашто поколение\"", "\"Ще бъдеш в бяло\" и \"При Рилския манастир\"", 1);
        addQuestion(q17,"dimchoDebelqnovTable");
        Question q18 = new Question("В епическа творба моментът на най-силно напрежение и върховен драматизъм, на най-голямо изостряне на конфликта се нарича:", "кулминация", "експозиция", "развръзка", "завръзка", 1);
        addQuestion(q18,"dimchoDebelqnovTable");
        Question q19 = new Question("От кое стихотворение на Димчо Дебелянов са стиховете? \n\"Аз умирам и светло се раждам -\nразнолика, нестройна душа,\nпрез деня неуморно изграждам,\nпрез нощта без пощада руша.\"", "\"Сиротна песен\"", "\"Черна песен\"", "\"Миг\"", "\"Пловдив\"", 2);
        addQuestion(q19,"dimchoDebelqnovTable");
        Question q20 = new Question("Кое от посочените твърдения НЕ е вярно за символизма като литературно направление?", "Поетите символисти се стремят към изящно съвършенство и мелодичност на стиха", "Според символизма видимата красота е външен израз на доброто в човешката душа", "Символистите правят аналогии между реалността, отвъдния свят и душата на човека", "Особеност на символизма е изобразяването на свръхестествени, демонични образи", 4);
        addQuestion(q20,"dimchoDebelqnovTable");
    }

    private void fillQuestionTableForHristoSmirnenski(){
        Question q1 = new Question("От къде е посоченият откъс? \n\"ледно тегне и души мъглата, - \nна живота сивата мъгла.\"", "\"Цветарка\"", "\"Миг\"", "\"Зимни вечери\"", "\"Маска\"", 3);
        addQuestion(q1,"hristoSmirnenskiTable");
        Question q2 = new Question("В кой ред всички стихотворения са написани от Христо Смирненски?", "\"Скрити вопли\", \"Цветарка\", \"Сенки\"", "\"Йохан\", \"Зимни вечери\", \"Ний\"", "\"Стария музикант\", \"Линее нашто поколение\", \"Маска\"", "\"Пловдив\", \"Да бъде ден!\", \"Тиха победа\"", 2);
        addQuestion(q2,"hristoSmirnenskiTable");
        Question q3 = new Question("Кое от посочените произведения на Христо Смирненски внушава идеята, че хората имат право да са творци на света, да заемат мястото на Бога и да заяват своята воля за живот?", "\"Зимни вечери\"", "\"Цветарка\"", "\"Ний\"", "\"Стария музикант\"", 3);
        addQuestion(q3,"hristoSmirnenskiTable");
        Question q4 = new Question("Усещането, че светът е несправедлив и трябва да бъде променен, е характерно за творчеството на:", "Пенчо Славейков", "Христо Смирненски", "Иван Вазов", "Елин Пелин", 2);
        addQuestion(q4,"hristoSmirnenskiTable");
        Question q5 = new Question("В епическа творба епизодът, в който се представят събития, станали след приключването на основната сюжетна линия, се нарича:", "експозиция", "епилог", "кулминация", "развръзка", 2);
        addQuestion(q5,"hristoSmirnenskiTable");
        Question q6 = new Question("Какво изразно средство НЕ откривате в стиховете? \n\"Като черна гробница и тая вечер\nпуст и мрачен е;\nтъпо стъпките отекват надалече\nи в тъмата се топят.\"", "сравнение", "алитерация", "хипербола", "метафора", 3);
        addQuestion(q6,"hristoSmirnenskiTable");
        Question q7 = new Question("Кое от посочените заглавия е цикъл от стихотворения?", "\"Зимни вечери\"", "\"Да бъде ден!\"", "\"Спи градът\"", "\"Септември\"", 1);
        addQuestion(q7,"hristoSmirnenskiTable");
        Question q8 = new Question("В коя от следните двойки стихотворения е представен социалният бунт на онеправданите?", "\"Един убит\" и \"Cis moll\"", "\"Стон\" и \"Черна песен\"", "\"Паисий\" и \"Две души\"", "\"Септември\" и \"Йохан\"", 4);
        addQuestion(q8,"hristoSmirnenskiTable");
        Question q9 = new Question("Коя от следните творби внушава идеята, че невинността, младостта и красотата са беззащитни и застрашени във връждебния град?", "\"Стария музикант\"", "\"Да бъде ден!\"", "\"Цветарка\"", "\"Ний\"", 3);
        addQuestion(q9,"hristoSmirnenskiTable");
        Question q10 = new Question("Кое от произведенията се отличава с композиционна рамка, образувана от повторението на първата и последната строфа?", "\"Да бъде ден!\"", "\"Спи градът\"", "\"Септември\"", "\"Цветарка\"", 2);
        addQuestion(q10,"hristoSmirnenskiTable");
        Question q11 = new Question("Христо Смирненски е представител на кое направление?", "романтизъм", "реализъм", "символизъм", "постсимволизъм", 4);
        addQuestion(q11,"hristoSmirnenskiTable");
        Question q12 = new Question("Стихотворенията \"Цветарка\" и \"Старият музикант\" са част от:", "не са част от цикъл и са издадени след смъртта на поета", "цикъла \"Зимни вечери\"", "стихосбирката \"Да бъде ден!\"", "цикъла \"Децата на града\"", 4);
        addQuestion(q12,"hristoSmirnenskiTable");
        Question q13 = new Question("Коя от изброените творби е елегия?", "\"Самотен гроб в самотен кът\" на Пенчо Славейков", "\"Цветарка\" на Христо Смирненски", "\"Пловдив\" на Димчо Дебелянов", "\"Заточеници\" на Яворов", 4);
        addQuestion(q13,"hristoSmirnenskiTable");
        Question q14 = new Question("Кой от следните мотиви НЕ откриваме в поезията на Смирненски?", "за урбанистичната действителност", "за патриархалната действителност", "за несправедливостта на света", "за страданието и унижението", 2);
        addQuestion(q14,"hristoSmirnenskiTable");
        Question q15 = new Question("Идеята за обречеността на невинността и красотата в един свят на разпаднали се ценности е разкрита в:", "\"Стария музикант\"", "\"Цветарка\"", "\"Йохан\"", "\"Да бъде ден!\"", 2);
        addQuestion(q15,"hristoSmirnenskiTable");
        Question q16 = new Question("\"Цветарка\", \"Стария музикант\" и \"Зимни вечери\" са обединени от темата за:", "устрема и екзалтацията на разбунтуваните тълпи", "страданието на беззащитните, отхвърлени от живота \"деца на града\"", "вярата на потиснатите хора в справедливото бъдеще", "спасителната мисия на онеправданите творци на живота", 2);
        addQuestion(q16,"hristoSmirnenskiTable");
        Question q17 = new Question(" В художественото пространство на коя творба мракът и мъглата са символи на безнадеждното страдалческо съществуване?", "\"Вяра\"", "\"Сиротна песен\"", "\"Зимни вечери\"", "\"Пловдив\"", 3);
        addQuestion(q17,"hristoSmirnenskiTable");
        Question q18 = new Question("Кое твърдение НЕ е вярно за творчеството на Христо Смирненски?", "В творчеството на Христо Смирненски бунтът на социално онеправданите е основна тема.", "В творчеството на Христо Смирненски лирически герои са бедните и страдащите хора на града.", "Творчеството на Христо Смирненски дава основание да бъде наречен \"поет на българското село\".", "В творчеството на Христо Смирненски се изразява надеждата, че старият свят ще бъде заменен от нов свят на справедливостта.", 3);
        addQuestion(q18,"hristoSmirnenskiTable");
        Question q19 = new Question("Кои цветове символи използва Христо Смирненски в поезията си", "черно, бяло, сребристо", "зелено, синьо, охра", "черно, жълто, сиво", "оранжево, кафяво, зелено", 3);
        addQuestion(q19,"hristoSmirnenskiTable");
        Question q20 = new Question("Коя от изброените творби е лирически текст?", "\"Серафим\"", "\"Чичовци\"", "\"Зимни вечери\"", "\"Андрешко\"", 3);
        addQuestion(q20,"hristoSmirnenskiTable");

    }

    private void fillQuestionTableForGeoMilev(){
        Question q1 = new Question("Думите \"Народът въстана / - с чук в ръката\" са от творба на:", "Гео Милев", "Иван Вазов", "Христо Ботев", "Алеко Константинов", 1);
        addQuestion(q1,"geoMilevTable");
        Question q2 = new Question("Коя от посочените творби НЕ е елегия?", "\"Ралица\"", "\"Cis moll\"", "\"Септември\"", "\"Елегия\"", 4);
        addQuestion(q2,"geoMilevTable");
        Question q3 = new Question("Кое от посочените твърдения НЕ е вярно?", "Поемата \"Септември\" е художествена реализация на идеята за бунта като социален, екзистенциален и естетически", "Началото на поемата \"Септември\" въвежда образа на слънчогледите като символ на надеждата и вярата", "В поемата на Гео Милев масата осъществява себе си, противопоставяйки се на установените правила и традиции", "Поемата \"Септември\" е изградена от 12 фрагмента, като седмият фрагмент съдържа само едно изречение", 2);
        addQuestion(q3,"geoMilevTable");
        Question q4 = new Question("Кой лирически образ не е част от художествения свят на поемата \"Септември\"", "\"разлютена милиция\"", "\"белоцветни вишни\"", "\"небесните мостове\"", "\"хиляди диви сърца\"", 2);
        addQuestion(q4,"geoMilevTable");
        Question q5 = new Question("В коя творба НЕ е интерпретиран мотивът за пътя?", "\"Две хубави очи\"", "\"Септември\"", "\"Спи градът\"", "\"На прощаване\"", 1);
        addQuestion(q5,"geoMilevTable");
        Question q6 = new Question("Кои от българските поети твори под въздействието на западноевропейския експресионизъм?", "Пенчо Славейков", "Димчо Дебелянов", "Гео Милев", "Пейо Яворов", 3);
        addQuestion(q6,"geoMilevTable");
        Question q7 = new Question("В кооя от посочените творби липсва епилог?", "\"Ралица\"", "\"Септември\"", "\"Моята молитва\"", "\"Дядо Йоцо гледа\"", 3);
        addQuestion(q7,"geoMilevTable");
        Question q8 = new Question("Какво е изразното средство в скобите? \n\"Войските настъпваха.\nПод грозния звук на шрапнелите\nизтръпнаха\nу най-смелите\nв отчаяние\n към небето издигнати голи (ръце)\"", "метафора", "оксиморон", "епитет", "синекдоха", 4);
        addQuestion(q8,"geoMilevTable");
        Question q9 = new Question("Кое от посочените твърдения НЕ е вярно за поемата \"Септември\"?", "\"Септември\" е поема, написана по повод историческо събитие от 1923г.", "Творбата се състои от 11 части, които са номерирани от автора", "Една от частите на произведението е съставена само от едно изречение", "В последната част на творбата присъстват антични и библейски мотиви", 2);
        addQuestion(q9,"geoMilevTable");
        Question q10 = new Question("Коя от следните опозиции НЕ присъства в поемата \"Септември\"", "родина - държава", "вяра - богоборчество", "индивид - колектив", "свобода - дълг", 4);
        addQuestion(q10,"geoMilevTable");
        Question q11 = new Question("Кое от древните библейско-митологични поверия НЕ са актуализира чрез сюжета на поемата \"Септември\":", "за деня на Страшния съд", "за световното дърво", "за първородния грях", "за сътворението", 3);
        addQuestion(q11,"geoMilevTable");
        Question q12 = new Question("Коя от посочените теми НЕ е интерпретирана в \"Септември\"", "за отечеството като свръхценност, достойна за преклонение", "за рухването на стария свят и пътя към бъдещето", "за \"варварството\" на \"цивилизиацията\"", "за бунта на \"варварството\" срещу \"цивилизиацията\"", 1);
        addQuestion(q12,"geoMilevTable");
        Question q13 = new Question("Емблематични за естетиката на експресионизма са понятията:", "бунт, фрагмент, интуиция, асоциация", "европеизация, личност, творец, духовна извисеност", "съответствия, нюанси, изящество, мелодика", "реализъм, обективност, изчерпателност, цялостнос", 1);
        addQuestion(q13,"geoMilevTable");
        Question q14 = new Question("\"Септември\" отразява събития от:", "1903г.", "1923г.", "1939г.", "1913г.", 2);
        addQuestion(q14,"geoMilevTable");
        Question q15 = new Question("Кое от посочените твърдения НЕ е вярно?", "V фрагмент в поемата \"Септември\" се състои от едно изречение, показващо драмата на случващото се", "XII фрагмент в поемата \"Септември\" играе ролята на епилог", "I фрагмент в поемата \"Септември\" играе ролята на пролог", "Сцената с поп Андрей в IX фрагмент е композиционен център на поемата \"Септември\"", 4);
        addQuestion(q15,"geoMilevTable");
        Question q16 = new Question("В коя от посочените творби се наблюдават елементи на експресионизма?", "\"Зимни вечери\" на Христо Смирненски", "\"Спи езерото\" на Пенчо Славейков", "\"Септември\" на Гео Милев", "\"Камък\" на Атанас Далчев", 3);
        addQuestion(q16,"geoMilevTable");
        //Question q17 = new Question("За експресионизма като литературно направление НЕ е вярно, че:", "естетизира грозното", "отличава се с фрагментарност", "възниква през 30-те години на XXв.", "използва провокацията", 3);
        //addQuestion(q17,"geoMilevTable");
        Question q18 = new Question("Кое твърдение НЕ е вярно?", "Атанас Далчев е активен член на литературния кръг \"Стрелец\"", "Поемата \"Септември\" е отпечатена през 1923г. в сп.\"Везни\"", "Първата стихосбирка на Елисавета Багряна - \"Вечната и святата\", излиза през 1927г.", "Издание на литературния кръг \"Мисъл\" е списание \"Мисъл\"", 2);
        addQuestion(q18,"geoMilevTable");
        Question q19 = new Question("Посочете НЕВЯРНОТО твърдение: ", " В стихотворението \"Ний\" звучи гласът на онеправданите \"деца на майката земя\".", "В стихотворението \"Юноша\" лирическият герой се наслаждава на младостта.", "Художественото пространство в \"Зимни вечери\" са крайните градски квартали.", " В \"Септември\" основна е темата за възхода и погрома на бунта на масите.", 2);
        addQuestion(q19,"geoMilevTable");
        Question q20 = new Question("Разрушаването на класическия стих и фрагментарността са водещи характеристики за поетическия език на:", "Христо Смирненски ", "Атанас Далчев", "Иван Вазов", "Гео Милев", 4);
        addQuestion(q20,"geoMilevTable");
        Question q21 = new Question("Кое от изброените произведения съдържа образи на божества и на митологични герои?", "\"Чорба от греховете на отец Никодим\"", "\"Септември\"", "\"Майце си\"", "\"Елате ни вижте\"" , 2);
        addQuestion(q21, "geoMilevTable");

    }

    private void  fillQuestionTableForAtanasDalchev(){
        Question q1 = new Question("От кое стихотворение са стиховете: \n\"И за да заглуша във себе си скръбта,\nпонякога аз сядам на прозореца\nи яростно оттам замервам хората\nсъс пръст от старите саксии без цветя.\"", "\"Дяволско\"", "\"Прозорец\"", "\"Стаята\"", "\"Къщата\"", 1);
        addQuestion(q1,"atanasDalchevTable");
        Question q2 = new Question("В кой ред всички стихотворения са написани от Атанас Далчев?", "\"Стаята\", \"Книгите\", \"Маска\"", "\"Дяволско\", \"Болница\", \"Сенки\"", "\"Къщата\", \"Прозорец\", \"Камък\"", "\"Повест\", \"Ний\", \"Да бъде ден!\"", 3);
        addQuestion(q2,"atanasDalchevTable");
        Question q3 = new Question("Иванчо Йотата и Мирончо са герои от творба на:", "Иван Вазов", "Атанас Далчев", "Йордан Йовков", "Елин Пелин", 1);
        addQuestion(q3,"atanasDalchevTable");
        Question q4 = new Question("Отчуждаването на човека от света е тема, характерна за творчеството на:", "Христо Смирненски", "Иван Вазов", "Елин Пелин", "Атанас Далчев", 4);
        addQuestion(q4,"atanasDalchevTable");
        Question q5 = new Question("С какво се отличава Далчевата самота от самотата на символистите?", "Лирическият герой е самотен и отчужден в своите \"безсъници\"", "Лирическият герой е \"бездомен и самин\"", "Предметният реализъм на вещите още по-силно отчуждава лирическия герой от света и той \"зъзне\" в своята болезнена самота", "Лирическият герой е \"самотник в тълпата\"", 3);
        addQuestion(q5,"atanasDalchevTable");
        Question q6 = new Question("Понятието \"стая\" е едноименното стихотворение на Атанас Далчев е:", "метафора", "синекдоха на къщата - дом", "пространство, в което човекът се самоизолира от другите", "синоним на сакралния дом на уюта и тишината", 2);
        addQuestion(q6,"atanasDalchevTable");
        Question q7 = new Question("Кои предмети в стихотворението \"Болница\" НЕ внушават страдание и неизбежна смърт?", "бялата варосана зала", "белите стени", "ръцете на болните, които търсят приятел и помощ", "\"прилепените бели легла\"", 3);
        addQuestion(q7,"atanasDalchevTable");
        Question q8 = new Question("Времето в стиховете на Далчев е осмислено преди всичко като:", "мяра за могъщата човешка деятелност", "съкровено, личностно, екзистенциално време", "като социално-историческо време", "съотношение между преходност и вечност", 2);
        addQuestion(q8,"atanasDalchevTable");
        Question q9 = new Question("Кое от изброените определения е най-неподходящо за проблематиката на Далчевата лирика:", "социална", "екзистенциална", "интимна", "философска", 1);
        addQuestion(q9,"atanasDalchevTable");
        Question q10 = new Question("Поезията на Атанас Далчев:", "се противопоставя на символистичната \"жреческа\" поза чрез човешкото начало и драмите на всекидневието", "се вписва в \"жреческата\" поезия на символизма", "се вписва в „жреческата” поезия на символизма", "потапя в бурята на страстите и емоциите, а не предпочита дистанцията на тяхното осмисляне", 1);
        addQuestion(q10,"atanasDalchevTable");
        Question q11 = new Question("В творчеството на кого от посочените автори НЕ се откриват идеите за бунта срещу потисничеството?", "Христо Ботев", "Иван Вазов", "Атанас Далчев", "Христо Смирненски" , 3);
        addQuestion(q11, "atanasDalchevTable");
        Question q12 = new Question("В творбите на кои двама автори отсъства мотивът за социалното страдание?", "Христо Ботев и Иван Вазов", "Пейо Яворов и Гео Милев", "Атанас Далчев и Елисавета Багряна", "Христо Смирненски и Елин Пелин", 3);
        addQuestion(q12,"atanasDalchevTable");
        Question q13 = new Question("Кое от изброените стихотворение е изградено само от три безглаголни изречения?", "\"Къщата\"", "\"Болница\"", "\"Стаята\"", "\"Сенки\"", 2);
        addQuestion(q13,"atanasDalchevTable");
        Question q14 = new Question("Кое от посочените твърдения НЕ е вярно?", "Творбата \"Книгите\" разглежда проблема за изгубения сред книгите живот", "Стихотворението \"Камък\" е философски размисъл за наближалата смърт", "Ключовият стих \"Стопанинът замина за Америка\" е от творбата \"Повест\"", "Творбата \"Дяволско\" е за драмата на самотния човек сред суетата на света", 1);
        addQuestion(q14,"atanasDalchevTable");
        Question q15 = new Question("Коя от посочените творби се откриват характерни елементи на диаболизма?", "\"Да се завърнеш в бащината къща\" на Димчо Дебелянов", "\"Къщата\" на Атанас Далчев", "\"На прощаване\" на Христо Ботев", "\"Прощално\" на Никола Вапцаров", 2);
        addQuestion(q15,"atanasDalchevTable");
        Question q16 = new Question("В коя от изброените творби художественият свят е диаболистистичен?", "\"Черна песен\" на Димчо Дебелянов", "\"Сенки\" на Пейо Яворов", "\"Дяволско\" на Атанас Далчев", "\"Кукувица\" от Елисавета Багряна", 3);
        addQuestion(q16,"atanasDalchevTable");
        Question q17 = new Question("Кое от посочените твърдения е вярно?", "В творчеството на Далчев се преплитат модернистичните търсения на българските поети от 20те - 40те години на XXв.", "Лириката на Вапцаров синтезира идеите и проблемите на 20те години на миналия век", "Художественият свят на Елин Пелин разкрива съдбата на българското село непосредствено след Освобождението", "Лириката на Багряна очертава образа на освободената от патриархалните окови жена в края на XIXв.", 1);
        addQuestion(q17,"atanasDalchevTable");
        Question q18 = new Question("В кое от посочените произведения присъства идеята за надмогването на личното страдание?", "\"Cis moll\" на Пенчо Славейков", "\"Миг\" на Димчо Дебелянов", "\"Зимни вичери\" на Христо Смирненски", "\"Стаята\" на Атанас Далчев", 4);
        addQuestion(q18,"atanasDalchevTable");
        Question q19 = new Question("За лириката на Атанас Далчев е характерно: ", "оживяването на предметите и овеществяването на човека", "противопоставянето на \"варварство\" и \"цивилизация\"", "въздействието чрез изящество и мелодиката на стиха", "изразяването на модерната душа чрез фолклорни  мотиви", 1);
        addQuestion(q19,"atanasDalchevTable");
        Question q20 = new Question("Заглавието на стихотворението \"Къщата\" е метафора на: ", "спокойствието и хармонията на човешкото битие", "празнотата и обречеността на човешкото съществуване", "защитеността на човека от предизвикателства на външния свят", "уюта и топлината на родния дом",  2);
        addQuestion(q20,"atanasDalchevTable");
    }

    private void fillQuestionTableForElisavetaBagrqna(){
        Question q1 = new Question("Кой от поетите представя жената като \"волната, скитница, непокорната\"?", "Христо Смирненски", "Пейо Яворов", "Атанас Далчев", "Елисавета Багряна", 4);
        addQuestion(q1,"elisavetaBagrqnaTable");
        Question q2 = new Question("В коя от посочените творби се открива мотивът за недостигнатите и неминати пътища?", "\"Камък\"", "\"Ний\"", "\"Стихии\"", "\"Ралица\"", 3);
        addQuestion(q2,"elisavetaBagrqnaTable");
        Question q3 = new Question("Кое от твърденията НЕ е вярно?", "В поезията на Багряна покоят ограничава стремежа към неизвестното и затова е отхвърлен", "При Багряна човекът копнее да прекрачи границата на един нов, криещ предизвикателства, свят", "Героинята в поезията на Багряна преоткрива себе си във фамилните книги, събрали паметта на времето", "Багряна съчетава устойчиви черти на народния светоглед и бит с модерни психологически решения", 3);
        addQuestion(q3,"elisavetaBagrqnaTable");
        Question q4 = new Question("Кой от посочените образи НЕ е от стихотворението на Елисавета Багряна \"Стихии\"?", "къщите и градинките", "боазите, диканите", "бъчвите огромни", "свилената прежда", 4);
        addQuestion(q4,"elisavetaBagrqnaTable");
        Question q5 = new Question("Кое от посочените произведения разкрива примиреното очакване на смъртта?", "\"Кукувица\"", "\"Болница\"", "\"Паисий\"", "\"Цветарка\"", 2);
        addQuestion(q5,"elisavetaBagrqnaTable");
        Question q6 = new Question("Кое от твърденията НЕ е вярно?", "Жената в лириката на Елисавета Багряна се подчинява на патриархалните традиции", "Жанрът лирическа миниатюра е характерен за творчеството на Пенчо Славейков", "Мотивът за дома и пътя е централен в поезията на Христо Ботве", "Героите в разказите на Йордан Йовков проявяват алтруизъм", 1);
        addQuestion(q6, "elisavetaBagrqnaTable");
        Question q7 = new Question("Кое от посочените произведения е елегия?", "\"Две хубави очи\" на Пейо Яворов", "\"Помниш ли, помниш ли..\" на Димчо Дебелянов", "\"Кукувица\" на Елисавета Багряна", "\"Ралица\" на Пенчо Славейков", 2);
        addQuestion(q7, "elisavetaBagrqnaTable");
        Question q8 = new Question("Усещането, че светът е несправедлив и трябва да бъде променен, НЕ е характерно за творчеството на:", "Гео Милев", "Елисавета Багряна", "Христо Смирненски", "Никола Вапцаров", 2);
        addQuestion(q8, "elisavetaBagrqnaTable");
        Question q9 = new Question("В кой ред всички стихотворения са написани от Елисавета Багряна?", "\"Кукувица\", \"Сиротна песен\", \"Песен за човека\"", "\"Стария музикант\", \"Сиротна песен\", \"Песен за човека\"", "\"Вечната\", \"Потомка\", \"Стихии\"", "\"Линее нашто поколение\", \"Дяволско\", \"Стихии\"", 3);
        addQuestion(q9, "elisavetaBagrqnaTable");
        Question q10 = new Question("В кой ред НЕ откривате символни образи, характерни за поезията на Елисавета Багряна?", "земята майка, кръвта", "слънчогледите, цветята", "родът, скитници", "пътят, вятърът", 2);
        addQuestion(q10, "elisavetaBagrqnaTable");
        Question q11 = new Question("Имената Мария и Анна се повтарят в творбата:" , "\"Вечната\"", "\"Цветарка\"", "\"Дяволско\"", "\"Кукувица\"", 1);
        addQuestion(q11, "elisavetaBagrqnaTable");
        Question q12 = new Question("От коя творба е посоченият откъс: \"Затова аз може би обичам\nнеобхватните поля,\nконски бяг под плясъка на бича,\nволен гласм по вятъра разлян.\n\nМоже би съм грешна и коварна,\nможе би средпът ще се сломя -\nаз съм само щерка твоя вярна,\nмоя кръвна майчице-земя.\"", "\"Стихии\"", "\"Потомка\"", "\"Вечната\"", "\"Кукувица\"", 2);
        addQuestion(q12,"elisavetaBagrqnaTable");
        Question q13 = new Question("За кой от посочените автори е характерно естетическото противопоставяне на символизма?", "Димчо Дебелянов", "Елисавета Багряна", "Христо Ботев", "ПЕйо Яворов", 2);
        addQuestion(q13,"elisavetaBagrqnaTable");
        Question q14 = new Question("Кой от посочените автори НЕ е представител на социалния реализъм в българската литература?", "Иван Вазов", "Елисавета Багряна", "Алеко Константинов", "Елин Пелин", 2);
        addQuestion(q14,"elisavetaBagrqnaTable");
        Question q15 = new Question("Образът на виталната жена е характерен за творчеството на: ", "Елисавета Багряна", "Пенчо Славейков", "Иван Вазов", "Алеко Константинов", 1);
        addQuestion(q15,"elisavetaBagrqnaTable");
        Question q16 = new Question("Коя особеност на лириката на Багряна я сродява с творчеството на Гео Милев и Атанас Далчев:", "темата за любовта", "образът на жената", "антисимволистичната естетика", "изображението на родното", 3);
        addQuestion(q16, "elisavetaBagrqnaTable");
        Question q17 = new Question("Лирическата героиня в \"Потомка\" избира за адресат на своята изповед:", "родината майка", "родната си майка", "любимия", "Бога", 1);
        addQuestion(q17, "elisavetaBagrqnaTable");
        Question q18 = new Question("Кое от посочените стихотворения резкрива идеята за родовата памет и духовната връзка с предците?", "\"Вечната\"", "\"Къщата\"", "\"Потомка\"", "\"Повест\"", 3);
        addQuestion(q18, "elisavetaBagrqnaTable");
        Question q19 = new Question("Лирическата героиня от стихотворението \"Кукувица\" е: ", "последователна пазителка на семейния уют", "волна и непокорна жена, копнееща за пътищата на света", "дтожер на родовия свят, на домашното огнище", "наследница на установените от векове патриархални традиции", 2);
        addQuestion(q19, "elisavetaBagrqnaTable");
        Question q20 = new Question("Идеята за избора на свободата и пътя пред семейството и дома е характерна за стихотворението: ", "\"Кукувица\"", "Вечната", "Стаята", "Прозорец", 1);
        addQuestion(q20, "elisavetaBagrqnaTable");
    }

    private void fillQuestionTableForYordanYovkov(){
        Question q1 = new Question("Кой от посочените Йовкови разкази поставя проблема за греха и изкуплението?", "\"Индже\"", "\"Последна радост\"", "\"Другоселец\"", "\"Серафим\"", 1);
        addQuestion(q1,"yordanYovkovTable");
        Question q2 = new Question("Каква композиционна функция изпълнява убийството на Индже от Найден Гърбавото в разказа \"Индже\" на Йордан Йовков?", "епилог", "експозиция", "развръзка", "завръзка", 3);
        addQuestion(q2,"yordanYovkovTable");
        Question q3 = new Question("Кое от посочените произведения внушава идеята, че трудът осмисля човешкия живот?", "\"Андрешко\"", "\"Занемелите камбани\"", "\"Песента на колелетата\"", "\"Албена\"", 3);
        addQuestion(q3,"yordanYovkovTable");
        Question q4 = new Question("Мотото \"Радка на порти стоеше, отдолу иде Мустафа…\" е от разказа:", "\"Последна радост\"", "\"През чумавото\"", "\"Другоселец\"", "\"Шибил\"", 4);
        addQuestion(q4,"yordanYovkovTable");
        Question q5 = new Question("В творчеството на кого от посочените автори се откроява мотивът за всепобеждаващата сила на красотата?", "Пейо Яворов", "Димитър Димов", "Йордан Йовков", "Елисавета Багряна", 3);
        addQuestion(q5,"yordanYovkovTable");
        Question q6 = new Question("Кой от изброените разкази на Йордан Йовков представя добротворството като смисъл на човешкия живот?", "\"Албена\"", "\"Шибил\"", "\"През чумавото\"", "\"Серафим\"", 4);
        addQuestion(q6,"yordanYovkovTable");
        Question q7 = new Question("В кой разказ осмислянето на доброто и злото променя героя:", "\"Шибил\"", "\"Последна радост\"", "\"Индже\"", "\"Песента на колелетата\"", 3);
        addQuestion(q7,"yordanYovkovTable");
        Question q8 = new Question("Тиха и Величко са герои от разакза:", "\"Мечтатели\"", "\"Последна радост\"", "\"През чумавото\"", "\"Задушница\"", 3);
        addQuestion(q8,"yordanYovkovTable");
        Question q9 = new Question("Завръзката в разказа \"Шибил\" е:", "срещата на Шибил и Рада в планината", "капанът на Мурад бей и Велико кехая", "предупрежденията на майката", "смъртта на Шибил и Рада", 1);
        addQuestion(q9,"yordanYovkovTable");
        Question q10 = new Question("Кое твърдение НЕ е вярно?", "В стихотворението \"Кукувица\" Елисавета Багряна използва фолклорно-митологични образи", "Основни композиционни елементи на Елин-Пелиновия разказ са диалогът и пейзажът", "Образът на благославящие Исус се появява във финала на разказа \"Последна радост\" на Йордан Йовков", "В стихотворението \"История\" на Никола Вапцаров се говори за \"простата човешка драма\"", 3);
        addQuestion(q10,"yordanYovkovTable");
        Question q11 = new Question("В кой от разказите е защитена идеята, че войната е \"безмилостна и страшна и главно - неясна и неоправдана\"?", "\"Занемелите камбани\"", "\"Последна радост\"", "\"Албена\"", "\"Серафим\"", 2);
        addQuestion(q11,"yordanYovkovTable");
        Question q12 = new Question("В коя от посочените творби човешкото творчество е смисъл, цел и оправдание на земното същестуване на човека?", "\"Песен за човека\"", "\"Цветарка\"", "\"Песента на колелетата\"", "\"Задушница\"", 3);
        addQuestion(q12,"yordanYovkovTable");
        Question q13 = new Question("В кой ред всички произведения са написани от Йордан Йовков?", "\"Занемелите камбани\", \"Шибил\", \"Серафим\"", "\"През чумавото\", \"Дядо Йоцо гледа\", \"На оня свят\"", "\"Последна радост\", \"Другоселец\", \"Песента на колелетата\"", "\"Албена\", \"Задушница\", \"Ветренета мелница\"", 3);
        addQuestion(q13,"yordanYovkovTable");
        Question q14 = new Question("В кой цикъл всички творби имат епиграф", "\"Под манастирската лоза\"", "\"Старопланински легенди\"", "\"Децата на града\"", "\"Епопея на забравените\"", 2);
        addQuestion(q14,"yordanYovkovTable");
        Question q15 = new Question("Кой е кулминационнит момент в разказа \"Другоселец\" от Йордан Йовков?", "разправията в кръчмата преди влизането на другоселеца в нея", "смъртта на коня", "когато другоселеца вади сгънатата на четири банкнота от 20лв.", "грижите на селяните за падналия кон", 3);
        addQuestion(q15,"yordanYovkovTable");
        Question q16 = new Question("Специфичната повествователна техника разказ в разказа НЕ се открива в:", "разказа \"Другоселец\"", "книгата \"Бай Ганьо\"", "разказа \"Чорба от греховете на отец Никодим\"", "разказа \"На оня свят\"", 1);
        addQuestion(q16,"yordanYovkovTable");
        Question q17 = new Question("Джапар и Шакире са герои от разказа:", "\"Последна радост\"", "\"Песента на колелетата\"", "\"Ветрената мелница\"", "\"Мечтатели\"", 2);
        addQuestion(q17,"yordanYovkovTable");

    }

    private void fillQuestionTableForNikolaVapcarov(){
        Question q1 = new Question("В кой ред всички стихотворения са написани от Никола Вапцаров?", "\"Завод\", \"Сън\", \"Песента на човека\"", "\"Кино\", \"Повест\", \"История\"", "\"Песен за човека\", \"Ний\", \"Прощално\"", "\"Вяра\", \"Борбата е безмилостно жестока..\", \"Сън\"", 4);
        addQuestion(q1,"nikolaVapcarovTable");
        Question q2 = new Question("В коя от посочените творби присъства образът на озъбено свирепо куче?", "\"История\"", "\"Завод\"", "\"Песен за човека\"", "\"Кино\"", 3);
        addQuestion(q2,"nikolaVapcarovTable");
        Question q3 = new Question("За творчеството на кого от посочените автори са характерни образите на завода, ракетата, куршумите?", "Христо Смирненски", "Гео Милев", "Никола Вапцаров", "Иван Вазов", 3);
        addQuestion(q3,"nikolaVapcarovTable");
        Question q4 = new Question("Романтиката и вярата са мотиви от творчеството на:", "Никола Вапцаров", "Христо Смирненски", "Христо Ботев", "Димчо Дебелянов", 1);
        addQuestion(q4,"nikolaVapcarovTable");
        Question q5 = new Question("Коя от посочените опозиции НЕ е от поезията на Никола Вапцаров?", "човекът и историята", "селото и градът", "сън и реалност", "мрак и светлина", 2);
        addQuestion(q5,"nikolaVapcarovTable");
        Question q6 = new Question("Кое от посочените твърдения НЕ е вярно?", "В България символизмът се появява в началото на XXв. и периодът му на разцвет е до времето на Балканската и на Първата световна война", "Поезията на Христо Смирненски отразява идейния и тематичния прелом в лириката на 20те години на XXв.", "Лириката на Никола Вапцаров очертава епохата от началото на XXв. до Първата световна война", "Фейлетоните на Алеко Константинов разкриват проблемите на българското общество през 90те години на XIXв.", 3);
        addQuestion(q6,"nikolaVapcarovTable");
        Question q7 = new Question("Коя от посочените творби НЕ е повлияна от фолклорната традиция?", "\"Кукувица\"", "\"Песен за човека\"", "\"На прощаване\"", "\"Ралица\"", 2);
        addQuestion(q7,"nikolaVapcarovTable");
        Question q8 = new Question("С кое историческо време е ангажирано творчеството на Никола Вапцаров?", "преди Освобождението", "между двете световни войни", "преди Първата световна война", "след Втората световна война", 2);
        addQuestion(q8,"nikolaVapcarovTable");
        Question q9 = new Question("Кой от посочените автори в лириката си води диалог с епохата, историята и живота?", "Иван Вазов", "Алеко Константинов", "Димитър Талев", "Никола Вапцаров", 4);
        addQuestion(q9,"nikolaVapcarovTable");
        Question q10 = new Question("Кой от изброените тематични кръгове е извън смисловия обхват на стихотворението \"Вяра\"?", "безусловната любов и привързаност на човека към живота", "вярата като жизненопотребен градивен елемент на човешкото съществуване", "ускореното техническо развитие на живота", "противоречивото отношение на човека към живота", 3);
        addQuestion(q10,"nikolaVapcarovTable");
        Question q11 = new Question("В \"Писмо\" лирическият герой изтъква като причина за душевното си страдание факта, че:", "се чувства обречен, притиснат в \"клетка\", хванат в \"капан\", лишен от правото да мечтае", "е пропилял живота си, енергията си, чувствата и дръзновението си", "изпитва носталгия по младостта си, изпълнена с екзотични спомени за Филипините и Фамагуста", "не е намерил смелост да пробие своя \"слепоочник\" и чрез смъртта да сложи край на безсилието и мъката си", 1);
        addQuestion(q11,"nikolaVapcarovTable");
        Question q12 = new Question("В предсмъртното си стихотворение \"Борбата е безмилостно жестока...\" Вапцаров:", "изповядва болката си на човек, който си отива преждевременно от света", "за пореден път заявява позицията си, основана на любовта към хората и вярата си в тяхното по-добро бъдеще", "е в позицията на романтически герой, който умира при изключителни обстоятелства", "патетично отстоява идеите си, заради които ще умре", 2);
        addQuestion(q12,"nikolaVapcarovTable");
        Question q13 = new Question("В кое от твърденията е формулирано основното послание на стихотворението \"Завод\"?", "Убийственият труд парализира желанието за живот и човешката деятелност", "Заводът е мрачна преизподня, в която не прониква лъч надежда", "Трудностите каляват волята за живот и раждат човешката солидарност", "\"Свирепият\" живот може да бъде преборен само със свирепост", 3);
        addQuestion(q13,"nikolaVapcarovTable");
        Question q14 = new Question("В стихотворението \"История\" лирическият глас води своя разговор с историята от името на:", "един неизвестен човек от народа", "целия български народ", "работниците, селяните, чиновниците, поетите", "трудовата класа", 2);
        addQuestion(q14,"nikolaVapcarovTable");
        Question q15 = new Question("Взаимноотношенита между Лори и лирическият глас в стихотворението \"Сън\" представляват:", "неосъществима мечта", "реална действителност", "лична илюзия", "социален блян", 4);
        addQuestion(q15,"nikolaVapcarovTable");
        Question q16 = new Question("Коя естетическа функция на седмото изкуство се пренебрегва в повърхностните развлекателни филми от 40те години на миналия век според Вапцаровия лирически човек от \"Кино\"?", "да провокира човешките чувства", "да дава знания за света", "да изобразява достоверно действителността", "да развива въображението", 3);
        addQuestion(q16,"nikolaVapcarovTable");
        Question q17 = new Question("Въпросът за нравствения прелов у човека е основен в стихотворението:" , "\"Прощално\"", "\"Песен за човека\"", "\"Завод\"", "\"История\"", 2);
        addQuestion(q17,"nikolaVapcarovTable");
        Question q18 = new Question("Съобразно смисловите си внушения и изградената поетическа атмосфера стихотворението \"Прощално\" може да бъде определено като:", "моментно разнежване от страна на рационалния и прагматичен лирически \"Аз\"", "връхна точка от развоя на революционната лирика от периода", "образец на интимната лирика", "баладична изповед", 3);
        addQuestion(q18,"nikolaVapcarovTable");
        Question q19 = new Question("В поезията на Вапцаров човекът е представен от гледна точка на:", "романтичен светоглед", "модернистичния светоглед", "хуманистичния светоглед", "пролетарския светоглед", 3);
        addQuestion(q19,"nikolaVapcarovTable");
        Question q20 = new Question("Лирическият герой във Вапцаровата поезия:", "се опитва да разгадае световните загадки и размишлява върху съдбата на човека", "търси своето място в живота и се бори за достойно съществуване", "изпитва омраза към класовия враг и налага своите пролетарски убеждения", "оспорва традиционните нравствени ценности и се стреми да изгради нови такива", 2);
        addQuestion(q20,"nikolaVapcarovTable");

    }

    private void fillQuestionTableForDimityrDimov(){
        Question q1 = new Question("Борис, Павел и Стефан са герои от творба на:", "Елин Пелин", "Димитър Димов", "Димитър Талев", "Иван Вазов", 2);
        addQuestion(q1,"dimityrDimovTable");
        Question q2 = new Question("Кое твърдение НЕ е вярно?", "В романа на Димитър Димов \"Тютюн\" се открояват сложните психологически преживявания на героите.", "Основната тема на романа на Димитър Димов \"Тютюн\" е устойчивостта на възрожденските идеали в българското общество.", "Романът на Димитър Димов \"Тютюн\" акцентира върху човешките инстинкти и стремежите за издигане в обществото.", "Романът на Димитър Димов \"Тютюн\" разглежда конфликти в българското общество преди и по време на Втората световна война.", 2);
        addQuestion(q2,"dimityrDimovTable");
        Question q3 = new Question("Коя от посочените творби поставя началото на модерния български роман?", "\"Под игото\"", "\"Тютюн\"", "\"Гераците\"", "\"Бай Ганьо\"", 2);
        addQuestion(q3,"dimityrDimovTable");
        Question q4 = new Question("Коя от изброените творби е \"социална епопея\" на българската действителност от 30те и 40те години на XXв.?", "\"Септември\"", "\"Чичовци\"", "\"Епопея на забравените\"", "\"Тютюн\"", 4);
        addQuestion(q4,"dimityrDimovTable");
        Question q5 = new Question("Какъв е времевият интервал на действието в романа \"Тютюн\"", "от началото на XXв. до Деветоюнския преврат през 1923г.", "от края на XIXв. до Звенарския преврат през 1934г.", "от 20те години на XXв. до Деветосептемврийския преврат през 1944г.", "от началото на XXв. до Деветосептемрвийския преврат през 1944г.", 3);
        addQuestion(q5,"dimityrDimovTable");
        Question q6 = new Question("За кой герой от романа \"Тютюн\" може да се каже, че свръхамбициите му заличават границите между доброто и злото, достойното и недостойното, моралното и неморалното, принципното и безпринципното:", "Павел Морев", "Борис Морев", "Стефан Морев", "Макс Ешкенази", 2);
        addQuestion(q6,"dimityrDimovTable");
        Question q7 = new Question("Нравствената деградация на Борис и Ирина е резултат от:", "устрема към реализация", "желанието за бърз успех", "погазването на моралните ценности", "стремежът към промяна на статуквото", 3);
        addQuestion(q7,"dimityrDimovTable");
        Question q8 = new Question("В коя от посочените творби се отркоява естетиката на реализма?", "\"Тютюн\" на Димитър Димов", "\"Септември\" на Гео Милев", "\"Къщата\" на Атанас Далчев", "\"Две души\" на Пейо Яворов", 1);
        addQuestion(q8,"dimityrDimovTable");
        Question q9 = new Question("Кое твърдение НЕ е вярно?", "Разказът \"Дядо Йоцо гледа\" разкрива разочерованието на Вазов от следосвобожденската действителност", "В \"Разни хора, разни идеали\" Алеко Константинов разкрива различните превъплъщения на човешката деградация", "Станчо и Стоилка са герои от разказа \"Задушница\" на Елин Пелин", "Светът на \"Никотиана\" е представен в романа \"Тютюн\" на Димитър Талев", 4);
        addQuestion(q9,"dimityrDimovTable");
        Question q10 = new Question("За кой от посочените герои от романа \"Тютюн\" НЕ е характерно желанието за материално благополучие?", "Ирина", "Павел Морев", "фон Гайер", "Борис Морев", 2);
        addQuestion(q10,"dimityrDimovTable");
        Question q11 = new Question("Кое от посочените произведения е роман?", "\"Ралица\"", "\"Къщата\"", "\"Чичовци\"", "\"Тютюн\"", 4);
        addQuestion(q11,"dimityrDimovTable");
        Question q12 = new Question("Къде всички герои са от произведение на Димитър Димов?", "Ирина, татко Пиер, Мирончо", "Варвара, Павел Морев, Лила", "Борис, Фон Гайер, Андрешко", "Костов, чичо Горан, Цветана", 2);
        addQuestion(q12,"dimityrDimovTable");
        Question q13 = new Question("Кое от посочените твърдения НЕ е вярно за творбата на Димитър Димов - \"Тютюн\"", "\"Никотиана\" е конкретен образ на акционерно дружество, но и умален модел на общественият социално-икономически живот", "Обсебени от стремежа да се издигнат в обществото, Ирина и Борис нравствено деградират и се превръщат в жертви на амбициите си", "Сред образите на героите от преуспяващия свят на концерна \"Никотиана\" са братята Павел и Стефан Мореви, Динко и Лукан", "Преработеното издание на \"Тютюн\" е с променени сюжетни моменти, добавени са герои, свързани с борбата за нов социален ред", 4);
        addQuestion(q13,"dimityrDimovTable");
        Question q14 = new Question("Къде и двете посочени героини нарушават установените патриархални традиции в името на любовта?", "Христина и Елка", "Ралица и Стоилка", "Ирина и Катерина", "Шакире и Цветана", 3);
        addQuestion(q14,"dimityrDimovTable");
        Question q15 = new Question("Кое от твърденията НЕ е вярно?", "Огледалото, часовникът, портретът са образи от поезията на Атанас Далчев", "Бунтът за социална справедливост е характерен мотив за поезията на Христо Смирненски", "Борбата за църковна независимост е мотив от романа \"Тютюн\" на Димитър Димов", "Човекът, домът и семейството за мотиви от романа на Димитър Талев \"Железният светилник\"", 3);
        addQuestion(q15,"dimityrDimovTable");
        Question q16 = new Question("Кое от посочените твърдения НЕ е вярно за романа \"Тютюн\"?", "Героите в романа никога не прекъсват връзките си с рода", "Основен в романа е конфликтът между греховно устроения свят и света на унижените и потиснатите", "Целият роман е изграден на принципа на контраста", "Романът разкрива драмата на човека в съвременния свят", 1);
        addQuestion(q16,"dimityrDimovTable");

    }

    private void fillQuestionTableForDimityrTalev(){
        Question q1 = new Question("Коя от посочените творби показва способността на хората да се обединят около национална идея?", "\"Железният светилник\"", "\"Да се завърнеш...\"", "\"Гераците\"", "\"Занемелите камбани\"", 1);
        addQuestion(q1,"dimityrTalevTable");
        Question q2 = new Question("Катерина и Рафе Клинче са герои от творбата:", "\"Под игото\"", "\"Железният светилник\"", "\"Тютюн\"", "\"Гераците\"", 2);
        addQuestion(q2,"dimityrTalevTable");
        Question q3 = new Question("В коя от посочените творби сюжетът се развива в историческия контекст на борбата на българския народ за независима църква във време на робство?", "\"Тютюн\"", "\"Дядо Йоцо гледа\"", "\"Железният светилник\"", "\"Разни хора, разни идеали\"", 3);
        addQuestion(q3,"dimityrTalevTable");
        Question q4 = new Question(" Кой от образите в романа на Димитър Талев „Железният светилник” въплъщава идеята за свободната творческа личност?", "Стоян Глаушев", "Рафе Клинче", "Климент Бенков", "Аврам Немтур", 2);
        addQuestion(q4,"dimityrTalevTable");
        Question q5 = new Question("Каква художествена функция изпълнява заглавието на романа \"Железният светилник\"?", "назовата тематиката", "очертава основната тематика", "изтъква епичвеския характер на повествованието", "представя символно изобразените исторически процеси", 4);
        addQuestion(q5,"dimityrTalevTable");
        Question q6 = new Question("Кое от твърденията НЕ е вярно?", "Централен в Йовковия сборник \"Старопланински легенди\" е проблемът за магическата сила на любовта и на красотата", "Поемата \"Септември\" на Гео Милев отрязява конкретно историческо събитие", "Романът \"Чичовци\" отрязява революционния подем на българите от епохата на Възраждането", "Романът \"Железният светилник\" пресъздава живота на българите в Македония", 3);
        addQuestion(q6,"dimityrTalevTable");
        Question q7 = new Question("За реализма в литературата е вярно, че:", "естетизира грозното", "отрязява следвоенната криза на ценностите", "изобрязява правдоподобно действителността", "разчита на провокацията", 3);
        addQuestion(q7,"dimityrTalevTable");
        Question q8 = new Question("Чакъра и Костов са герои от:", "романа \"Железнит светилник\"", "романа \"Тютюн\"", "романа \"Под игото\"", "повестта \"Гераците\"", 2);
        addQuestion(q8,"dimityrTalevTable");
        Question q9 = new Question("Катерина и Лазар са герои от:", "\"Ветрената мелница\"", "\"Чичовци\"", "\"Тютюн\"", "\"Железният светилник\"", 4);
        addQuestion(q9,"dimityrTalevTable");
        Question q10 = new Question("В коя от посочените творби всички епиграфи на отделните й части са откъси от народни песни?", "\"Старопланински легенди\"", "\"Железният светилник\"", "\"Под игото\"", "\"Чичовци\"", 2);
        addQuestion(q10,"dimityrTalevTable");
        Question q11 = new Question("В кой ред сюжетно-композиционнните елементи са подредени в тяхната последователност?", "епилог, експозиция, развръзка, кулминация, завръзка", "завръзка, развръзка, епилог, кулминация, експозиция", "експозиция, завръзка, кулминация, развръзка, епилог", "експозиция, завръзка, развръзка, кулминация, епилог", 3);
        addQuestion(q11,"dimityrTalevTable");
        Question q12 = new Question("В коя двойка от изброените произведения индивидуалната съдба на героите е представена на фона на исторически процеси и събития?", "\"Ветрената мелница\" и \"Андрешко\"", "\"Песента на колелетата\" и \"Албена\"", "\"Да бъде ден\" и \"Старият музикант\"", "\"Железният светилник\" и \"Тютюн\"", 4);
        addQuestion(q12,"dimityrTalevTable");
        Question q13 = new Question("Къде всички герои са от творба на Димитър Талев", "Катерина, хаджи Драган, отец Сисой", "Стоян Глаушев, баба Марга, Рустем", "Лазар Глаушев, Султана, Андрешко", "Ния, Рафе Клинче, Климент Бенков", 4);
        addQuestion(q13,"dimityrTalevTable");
        Question q14 = new Question("Кое от изброените твърдения НЕ е вярно за творбата на Димитър Талев - \"Железният светилник\"?", "В творбата ярко са откроени пространството на семейните, националните и общочовешките ценности", "Събитията, залегнали в сюжета, са обединени около историята на един род в малкото градче Преслав", "Султана, като единствена наследница на Хаджи-Серафимовото семейство, е призвана да продължи рода", "Образът на Лазар носи идеята за надмогване на изпитанията, за историческата перспектива пред народа", 2);
        addQuestion(q14,"dimityrTalevTable");
        Question q15 = new Question("Конфликтът между обществената ангажираност и интимните чувства е характерен за:", "разказа \"На оня свят\"", "поемата \"Септември\"", "повестта \"Гераците\"", "романа \"Железният светилник\"", 4);
        addQuestion(q15,"dimityrTalevTable");
        Question q16 = new Question("Град Преспа е място на действието в:", "повестта \"Чичовци\"", "разказа \"Андрешко\"", "романа \"Железният светилник\"", "поемата \"Ралица\"", 3);
        addQuestion(q16,"dimityrTalevTable");
        Question q17 = new Question("В коя от посочените творби НЕ присъства мотива за освобождението на българите от робството?", "\"Железният светилник\"", "\"Под игото\"", "\"Градушка\"", "\"На прощаване\"", 3);
        addQuestion(q17,"dimityrTalevTable");
        Question q18 = new Question("Родовото начало, съхраняването на патриархалните традиции, бунтът срещу предразсъдаците са основни мотиви в:", "\"Тютюн\" на Димитър Димов", "\"Железният светилник\" на Димитър Талев", "\"Под манастирката лоза\" на Елин Пелин", "\"Другоселец\" на Йордан Йовков", 2);
        addQuestion(q18,"dimityrTalevTable");
        Question q19 = new Question("Епилогът на романа \"Железният светилник\" е:", "смъртта на Катерина", "построяването на църквата", "сватбата на Лазар и Ния", "посещението на рилския монах", 3);
        addQuestion(q19,"dimityrTalevTable");
    }


    private void addQuestion(Question question, String tableName){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(tableName, null, cv);
        //db.close();
    }


    public List<Question> belAllQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "belQuestionsTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> hristoBotevetAllQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + nameOfTable, null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> ivanVazovAllQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "ivanVazovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> alekoKonstantinovQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "alekoKonstantinovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> penchoSlaveikovQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "penchoSlaveikovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> peyoQvorovQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "peyoQvorovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }


    public List<Question> elinPelinQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "elinPelinTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> dimchoDebelqnovTable(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "dimchoDebelqnovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> hristoSmirnenskiQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "hristoSmirnenskiTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> geoMilevQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "geoMilevTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> atanasDalchevQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "atanasDalchevTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> elisavetaBagrqnaQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "elisavetaBagrqnaTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> yordanYovkovQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "yordanYovkovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> nikolaVapcarovQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "nikolaVapcarovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> dimityrDimovQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "dimityrDimovTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }

    public List<Question> dimityrTalevQuestions(){
        List<Question> questions = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + "dimityrTalevTable", null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questions.add(question);
            }while (c.moveToNext());
        }

        c.close();

        return questions;
    }


    //dimityrTalevTable

}
