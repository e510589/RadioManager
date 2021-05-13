package hf.yz.hfradiomanager_v2;

public class TextAPI {

    private TestCallBack mTestCallBack;
    private int number_a;
    private int number_b;

    public TextAPI(int a, int b) {
        this.number_a = a;
        this.number_b = b;
    }

    public void registerCallBack(TestCallBack testCallBack){
        mTestCallBack = testCallBack;
    }

    public void getResult(){
        int r = number_a-number_b;

        if(r > 0){
            mTestCallBack.onFinished();
        }else{
            mTestCallBack.onFailed();
        }
    }
}
