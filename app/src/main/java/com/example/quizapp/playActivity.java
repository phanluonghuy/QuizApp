package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class playActivity extends AppCompatActivity {
    List<Question> questionList = generate_question();

    TextView cpt_question , text_question, text_result;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;


    int currentQuestion =  0  ;
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);
        text_result = findViewById(R.id.text_result);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()
        );
        Collections.shuffle(questionList);
        remplirData();
        btn_next.setOnClickListener(
                view -> {
                        if (isclickBtn){
                            isclickBtn = false;

                            if(!valueChoose.equals(questionList.get(currentQuestion).getAnswer())){
                                text_result.setVisibility(View.VISIBLE);
                                text_result.setText("Wrong");
                                text_result.setTextColor(Color.parseColor("#FF0000"));
                                btn_click.setBackgroundResource(R.drawable.background_btn_error);

                            }else {
                                text_result.setVisibility(View.VISIBLE);
                                text_result.setText("Correct");
                                text_result.setTextColor(Color.parseColor("#42DF00"));
                                btn_click.setBackgroundResource(R.drawable.background_btn_correct);

                                scorePlayer++;
                            }
                            new Handler().postDelayed(() -> {
                                if(currentQuestion < 19 ){
                                    text_result.setVisibility(View.INVISIBLE);
                                    currentQuestion = currentQuestion + 1;
                                    remplirData();
                                    valueChoose = "";
                                    btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);

                                }else {
                                    Intent intent  = new Intent(playActivity.this , ResultActivity.class);
                                    intent.putExtra("Result" , scorePlayer);
                                    startActivity(intent);
                                    finish();
                                }

                            },2000);

                        }else {
                            Toast.makeText(playActivity.this ,  "You must choose one !",Toast.LENGTH_LONG).show();
                        }
                }
        );


    }

    void remplirData(){
        Question question_ = questionList.get(currentQuestion);
        cpt_question.setText((currentQuestion+1) + "/" + "20");
        text_question.setText(question_.getQuestion());

        btn_choose3.setVisibility(View.GONE);
        btn_choose4.setVisibility(View.GONE);

        btn_choose1.setText(question_.getChoices().get(0));
        btn_choose2.setText(question_.getChoices().get(1));
        if (question_.getChoices().size()==2) return;
        btn_choose3.setVisibility(View.VISIBLE);
        btn_choose3.setText(question_.getChoices().get(2));
        if (question_.getChoices().size()==3) return;
        btn_choose4.setVisibility(View.VISIBLE);
        btn_choose4.setText(question_.getChoices().get(3));

    }

    public void ClickChoose(View view) {
        btn_click = (Button)view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }
    void chooseBtn(){

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
    public List<Question> generate_question() {
        List<Question> questionList = new ArrayList<>();

        questionList.add(new Question("Người lái xe không được quay đầu xe tại các khu vực nào nêu dưới đây?", Arrays.asList(new String[]{"Nơi đường giao nhau.", "Trên cầu."}), "Trên cầu."));

        questionList.add(new Question(
                "Người lái xe không được quay đầu xe tại các khu vực nào nêu dưới đây?",
                Arrays.asList("Gầm cầu vượt.", "Nơi đường giao nhau.", "Nơi có biển báo cho phép quay đầu xe."),
                "Gầm cầu vượt."
        ));
        questionList.add(new Question(
                "Người lái xe không được quay đầu xe tại các khu vực nào nêu dưới đây?",
                Arrays.asList("Nơi đường giao nhau.", "Nơi có biển báo cho phép quay đầu xe.", "Đường ngầm."),
                "Đường ngầm."
        ));
        questionList.add(new Question(
                "Người lái xe không được quay đầu xe tại các khu vực nào nêu dưới đây?",
                Arrays.asList("Nơi có biển báo cho phép quay đầu xe.", "Nơi đường giao nhau.", "Nơi đường bộ giao nhau cùng mức với đường sắt."),
                "Nơi đường bộ giao nhau cùng mức với đường sắt."
        ));
        questionList.add(new Question(
                "Trên cầu đường bộ đi chung với đường sắt thì loại phương tiện nào được quyền ưu tiên đi trước?",
                Arrays.asList("Phương tiện nào bên phải không vướng.", "Phương tiện nào ra tín hiệu xin đường trước.", "Phương tiện giao thông đường sắt."),
                "Phương tiện giao thông đường sắt."
        ));
        questionList.add(new Question(
                "Hành vi đua xe, cổ vũ đua xe, tổ chức đua xe trái phép trên đường bộ có bị nghiêm cấm không?",
                Arrays.asList("Không nghiêm cấm.", "Bị nghiêm cấm tùy theo các tuyến đường.", "Bị nghiêm cấm.", "Bị nghiêm cấm tuỳ theo loại xe."),
                "Bị nghiêm cấm."
        ));
        questionList.add(new Question(
                "Những hành vi nào ghi dưới đây bị nghiêm cấm ?",
                Arrays.asList("Lạng lách, đánh võng trên đường bộ.", "Thay đổi tốc độ của xe trên đường bộ.", "Thay đổi tay số của xe trên đường bộ."),
                "Lạng lách, đánh võng trên đường bộ."
        ));

        questionList.add(new Question(
                "Những chất nào ghi dưới đây bị nghiêm cấm trong cơ thể người điều khiển phương tiện giao thông đường bộ?",
                Arrays.asList("Chất ma túy.", "Chất đạm.", "Chất khoáng."),
                "Chất ma túy."
        ));

        questionList.add(new Question(
                "Người điều khiển xe ô tô, máy kéo, xe máy chuyên dùng trên đường mà trong máu hoặc hơi thở có nồng độ cồn có bị nghiêm cấm không ?",
                Arrays.asList("Bị nghiêm cấm.", "Không bị nghiêm cấm.", "Không bị nghiêm cấm, nếu nồng độ cồn trong máu ở mức nhẹ, có thể điều khiển phương tiện tham gia giao thông."),
                "Bị nghiêm cấm."
        ));

        questionList.add(new Question(
                "Những hành vi nào ghi dưới đây bị nghiêm cấm?",
                Arrays.asList("Điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu.", "Điều khiển xe cơ giới chạy chưa quá tốc độ tối đa cho phép.", "Điều khiển xe cơ giới chạy quá tốc độ tối thiểu cho phép."),
                "Điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu."
        ));

        questionList.add(new Question(
                "Khi xảy ra tai nạn giao thông, những hành vi nào ghi ở dưới đây bị nghiêm cấm?",
                Arrays.asList("Cứu giúp người bị tai nạn giao thông.", "Bảo vệ tài sản của người bị nạn và người gây tai nạn.", "Bỏ trốn sau khi gây tai nạn để trốn tránh trách nhiệm."),
                "Bỏ trốn sau khi gây tai nạn để trốn tránh trách nhiệm."
        ));

        questionList.add(new Question(
                "Khi xảy ra tai nạn giao thông, những hành vi nào ghi ở dưới đây bị nghiêm cấm?",
                Arrays.asList("Xâm phạm tính mạng, sức khoẻ, tài sản của người bị nạn và người gây tai nạn.", "Sơ cứu người bị nạn khi cơ quan có thẩm quyền chưa cho phép.", "Sơ cứu người gây tai nạn khi cơ quan có thẩm quyền chưa cho phép."),
                "Xâm phạm tính mạng, sức khoẻ, tài sản của người bị nạn và người gây tai nạn."
        ));

        questionList.add(new Question(
                "Người lái xe không được vượt xe khác khi gặp trường hợp nào ghi ở dưới đây?",
                Arrays.asList("Trên cầu hẹp có một làn xe.", "Nơi đường bộ giao nhau không cùng mức với đường sắt.", "Xe được quyền ưu tiên đang đi phía trước nhưng không phát tín hiệu ưu tiên đi làm nhiệm vụ."),
                "Trên cầu hẹp có một làn xe."
        ));
        questionList.add(new Question(
                "Ở những nơi nào không được quay đầu xe?",
                Arrays.asList("Trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc."),
                "Trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc."
        ));

        questionList.add(new Question(
                "Ở những nơi nào nêu dưới đây, người lái xe không được lùi xe?",
                Arrays.asList("Nơi đường bộ giao nhau không cùng mức với đường sắt.", "Nơi tầm nhìn bị che khuất, trong hầm đường bộ, đường cao tốc."),
                "Nơi tầm nhìn bị che khuất, trong hầm đường bộ, đường cao tốc."
        ));
        questionList.add(new Question(
                "Khi điều khiển xe chạy trên đoạn đường vòng, đầu dốc và các vị trí có tầm nhìn hạn chế, người lái xe muốn vượt xe khác thì phải xử lý như thế nào?",
                Arrays.asList(
                        "Nháy đèn pha kết hợp với tín hiệu còi cho xe trước biết để xe mình vượt.",
                        "Không được vượt.",
                        "Nếu thấy không có xe đi ngược chiều và đường đủ rộng thì có thể cho xe vượt nhưng phải bảo đảm an toàn."
                ),
                "Không được vượt."
        ));
        questionList.add(new Question(
                "Người lái xe phải làm gì khi quay đầu xe trên cầu, gầm cầu vượt, đường ngầm hay khu vực đường bộ giao nhau cùng mức với đường sắt?",
                Arrays.asList(
                        "Không được quay đầu xe.",
                        "Lợi dụng chỗ rộng và phải có người làm tín hiệu sau xe để bảo đảm an toàn.",
                        "Lợi dụng chỗ rộng có thể quay đầu được để quay đầu xe cho an toàn."
                ),
                "Không được quay đầu xe."
        ));
        questionList.add(new Question(
                "Ban đêm xe cơ giới đi ngược chiều gặp nhau, đèn chiếu sáng phải được sử dụng như thế nào?",
                Arrays.asList(
                        "Phải chuyển từ đèn chiếu gần sang đèn chiếu xa.",
                        "Phải chuyển từ đèn chiếu xa sang đèn chiếu gần.",
                        "Phải chuyển đèn chiếu gần, xa liên tục để báo hiệu."
                ),
                "Phải chuyển từ đèn chiếu xa sang đèn chiếu gần."
        ));
        questionList.add(new Question(
                "Người lái xe phải nhanh chóng giảm tốc độ, tránh hoặc dừng lại sát lề đường bên phải để nhường đường cho các xe nào nêu dưới đây?",
                Arrays.asList(
                        "Xe chữa cháy, xe quân sự, xe công an, xe cứu thương, xe hộ đê sau khi thực hiện nhiệm vụ khẩn cấp, không có tín hiệu còi, cờ, đèn theo quy định của pháp luật.",
                        "Xe chữa cháy, xe quân sự, xe công an, xe cứu thương, xe hộ đê đi làm nhiệm vụ khẩn cấp có tín hiệu còi, cờ, đèn theo quy định của pháp luật.",
                        "Xe ô tô, xe máy, đoàn xe đang diễu hành có tổ chức có báo tín hiệu xin vượt bằng còi và đèn."
                ),
                "Xe chữa cháy, xe quân sự, xe công an, xe cứu thương, xe hộ đê đi làm nhiệm vụ khẩn cấp có tín hiệu còi, cờ, đèn theo quy định của pháp luật."
        ));
        questionList.add(new Question(
                "Trong các loại xe nêu dưới đây, người lái xe phải nhường đường cho xe nào đi trước khi qua đường giao nhau?",
                Arrays.asList(
                        "Xe trên đường nhánh.",
                        "Xe trên đường không ưu tiên.",
                        "Xe chữa cháy đi làm nhiệm vụ."
                ),
                "Xe chữa cháy đi làm nhiệm vụ."
        ));
        questionList.add(new Question(
                "Tại nơi đường giao nhau có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?",
                Arrays.asList(
                        "Phải nhường đường cho xe đi bên phải.",
                        "Xe báo hiệu xin đường trước, xe đó được đi trước.",
                        "Phải nhường đường cho xe đi bên trái."
                ),
                "Phải nhường đường cho xe đi bên trái."
        ));
        questionList.add(new Question(
                "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải xử lý như thế nào là đúng quy tắc giao thông?",
                Arrays.asList(
                        "Tăng tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.",
                        "Giảm tốc độ qua đường giao nhau để đi trước xe đi trên đường ưu tiên.",
                        "Nhường đường cho xe đi trên đường ưu tiên từ bất kỳ hướng nào tới."
                ),
                "Nhường đường cho xe đi trên đường ưu tiên từ bất kỳ hướng nào tới."
        ));

        questionList.add(new Question(
                "Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?",
                Arrays.asList(
                        "Phải nhường đường cho xe đi đến từ bên phải.",
                        "Xe báo hiệu xin đường trước xe đó được đi trước.",
                        "Phải nhường đường cho xe đi đến từ bên trái."
                ),
                "Phải nhường đường cho xe đi đến từ bên phải."
        ));
        questionList.add(new Question(
                "Trên đoạn đường bộ giao nhau cùng mức với đường sắt thì loại phương tiện nào được quyền ưu tiên đi trước?",
                Arrays.asList(
                        "Phương tiện nào bên phải không vướng.",
                        "Phương tiện nào ra tín hiệu xin đường trước.",
                        "Phương tiện giao thông đường sắt."
                ),
                "Phương tiện giao thông đường sắt."
        ));
        questionList.add(new Question(
                "Người ngồi trên xe mô tô 2 bánh, xe mô tô 3 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách khi nào?",
                Arrays.asList(
                        "Khi tham gia giao thông đường bộ.",
                        "Chỉ khi đi trên đường chuyên dùng; đường cao tốc."
                ),
                "Khi tham gia giao thông đường bộ."
        ));
        questionList.add(new Question(
                "Khi tham gia giao thông đường bộ, người ngồi trên xe mô tô 2 bánh, xe mô tô 3 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách đúng hay không?",
                Arrays.asList(
                        "Phải đội mũ bảo hiểm có cài quai đúng quy cách.",
                        "Không bắt buộc đội mũ bảo hiểm.",
                        "Tùy từng trường hợp."
                ),
                "Phải đội mũ bảo hiểm có cài quai đúng quy cách."
        ));
        questionList.add(new Question(
                "Người điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy không được thực hiện những hành vi nào dưới đây?",
                Arrays.asList(
                        "Đội mũ bảo hiểm có cài quai đúng quy cách.",
                        "Chở 02 người; trong đó, có người bệnh đi cấp cứu.",
                        "Chở 02 người; trong đó, có trẻ em dưới 14 tuổi.",
                        "Đi xe dàn hàng ngang; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính)."
                ),
                "Đi xe dàn hàng ngang; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính)."
        ));
        questionList.add(new Question(
                "Người điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy có được đi xe dàn hàng ngang; sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?",
                Arrays.asList(
                        "Được phép nhưng phải đảm bảo an toàn.",
                        "Không được phép."
                ),
                "Không được phép."
        ));
        questionList.add(new Question(
                "Khi gặp một đoàn xe, một đoàn xe tang hay gặp một đoàn người có tổ chức theo đội ngũ, người lái xe phải xử lý như thế nào?",
                Arrays.asList(
                        "Bóp còi, rú ga để cắt qua đoàn người, đoàn xe.",
                        "Không được cắt ngang qua đoàn người, đoàn xe.",
                        "Báo hiệu từ từ cho xe đi qua để bảo đảm an toàn."
                ),
                "Không được cắt ngang qua đoàn người, đoàn xe."
        ));


        return questionList;
    }
}