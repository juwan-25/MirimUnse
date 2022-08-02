package kr.hs.s2104.mirimunse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Fortunes.db";
    private static final String DB_TABLE_RECORD = "ForturnesRecord_Table";
    private static final String DB_TABLE_CONTENT = "ForturnesContent_Table";

    //columns
    private static final String TITLE = "TITLE";
    private static final String DATE = "DATE";
    private static final String CONTEXTS = "CONTEXTS";
    private static final String IMG_ID = "IMG_ID";

    //content
    private static final String richesGoodTitle = "매점에서 좋은 일이 일어날 것 같은 날이야!";
    private static final String richesBadTitle = "소비를 조심해야하는 날이야!";
    private static final String friendshipGoodTitle = "좋은 친구들을 많이 마주칠 거 같은 날이야!";
    private static final String friendshipBadTitle = "그냥 뱉은 말도 친한 친구에게 상처가 될 수 있는 날이야!";
    private static final String studyGoodTitle = "찍신 강림~! 운빨로 지낼 수 있는 날이야!";
    private static final String studyBadTitle = "당장 일정을 확인해야하는 날이야!";
    private static final String healthGoodTitle = "당신이 오늘의 체육 왕~ 컨디션 짱";
    private static final String healthBadTitle = "계단 오를때 조심해야하는 날";
    private static final String employGoodTitle = "면접관 앞에서도 말이 술술술~";
    private static final String employBadTitle = "용모단정! 언제 어디에서 누가 우릴 보고 있을지 모르는 날이야!";

    private static final String richesGoodContext = "매점을 들려 맛있는 간식을 사먹는다면.. 행복해지고 말 거야\n매점 아주머니께서 맛있는 걸 덤으로 주실지도!!";
    private static final String richesBadContext = "배고픔에 눈이 멀어 충동소비를 할지도 몰라\n급식을 배부르게 꼭꼭 씹어먹도록 하자";
    private static final String friendshipGoodContext = "오늘은 새로운 친구를 사귀어도 좋을 거 같아!!\n좋은 친구들이 주변에 가득 모이겠는걸~?\n새로운 프로젝트를 시작하거나 동아리 활동 같은 팀 활동을 하기 딱 좋지!!!\n";
    private static final String friendshipBadContext = "평소 친한친구에게 툭 뱉던 말들도 오늘만큼은 생각하고 말해야 해ㅜㅜ\n크게 싸움이 일어나거나 머리채 잡힐 수 있으니 조심하자~";
    private static final String studyGoodContext = "하루종일 모든 일이 술술 다 풀릴 거야\n아닌 것 같아도 잘 풀린다고 생각해보자 :)\n긍정적인 하루를 위하여- (위하여-)\n";
    private static final String studyBadContext = "곧 있을 수행평가나 마감기한이 없는지 꼭 확인해보자\n꼼꼼하게 체크하면 좋은 결과가 널 기다릴 거야\n";
    private static final String healthGoodContext = "오늘의 미림 체육왕은 바로 나야 나~!❤\n미림체육듀스 101 일짱이 되어보자구~~\n";
    private static final String healthBadContext = "누가 밀걸레로 계단을 열심히 닦았나 봐..\n(미끄덩)어이쿠;;;\n다치지 않게 오늘 하루도 조심하는게 좋은 하루야\n";
    private static final String employGoodContext = "오늘만큼은 나도 프로 개발자!!!\n음~ 원하던 직장에 취업할 수 있겠군…\n계획대로 “착착” 진행되는 하루\n";
    private static final String employBadContext = "이런 오늘 하필 교복 리본을 안 가져왔다니..\n면접 30분 전에 알게 된 사실\n오늘은 미림의 “쥬쥬”를 조심하라궁~!(데헷><)\n";

    private static final String CREATE_TABLE_RECORD = "CREATE TABLE "+DB_TABLE_RECORD+" ("+
            TITLE+" TEXT, "+
            DATE+" DATE,"+
            CONTEXTS+" TEXT "+")";

    private static final String CREATE_TABLE_CONTENT = "CREATE TABLE "+DB_TABLE_CONTENT+" ("+
            IMG_ID+" INTEGER,"+
            TITLE+" TEXT, "+
            CONTEXTS+" TEXT "+")";


//    private static final String INSERT_TABLE_CONTENT = "INSERT INTO "+DB_TABLE_CONTENT+
//            "VALUES ("+R.drawable.img_riches_good+", "+richesGoodTitle+", "+richesGoodContext+"), "+
//            "("+R.drawable.img_riches_bad+", "+richesBadTitle+", "+richesBadContext+"), "+
//            "("+R.drawable.img_friendship_good+", "+friendshipGoodTitle+", "+friendshipGoodContext+"), "+
//            "("+R.drawable.img_friendship_bad+", "+friendshipBadTitle+", "+friendshipBadContext+"), "+
//            "("+R.drawable.img_riches_good+", "+studyGoodTitle+", "+studyGoodContext+"), "+
//            "("+R.drawable.img_riches_bad+", "+studyBadTitle+", "+studyBadContext+"), "+
//            "("+R.drawable.img_riches_good+", "+healthGoodTitle+", "+healthGoodContext+"), "+
//            "("+R.drawable.img_riches_bad+", "+healthBadTitle+", "+healthBadContext+"), "+
//            "("+R.drawable.img_riches_good+", "+employGoodTitle+", "+employGoodContext+"), "+
//            "("+R.drawable.img_riches_bad+", "+employBadTitle+", "+employBadContext+"); ";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_RECORD);
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTENT);
//        sqLiteDatabase.execSQL(INSERT_TABLE_CONTENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }

    //데이터 삽입 메서드
    public boolean insertData(String title, String date, String contents){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        long result = db.insert(DB_TABLE_RECORD, null, contentValues);

        contentValues.put(DATE, date);
        result = db.insert(DB_TABLE_RECORD, null, contentValues);

        contentValues.put(CONTEXTS, contents);
        result = db.insert(DB_TABLE_RECORD, null, contentValues);

        return result != -1; //if result = -1 data dodsent insert
    }

    //데이터 SELECT 메서드
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE_RECORD;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

}
