package com.jcw.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * @author 无极侠岚
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 定义需要的常量。
     */
    private String ERROR = "error", ADD = "+", SUB = "-", MUL = "*",
            DIV = "/", LEFT = "(", RIGHT = ")", MOD = "%";
    /**
     * left 左括号， right 右括号，div 除，mod 取余， mul 乘，sub 减，add 加, point 小数点
     */
    private Button left, right, div, mod, mul, sub, add, point;
    /**
     * num0: 0, num1: 1，num2：2，num3: 3，num4：4，num5：5，num6: 6，num7: 7，num8: 8，num9: 9
     */
    private Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    /**
     * 定义输入文本框
     */
    private EditText calculator;
    /**
     * 定义显示框
     */
    private TextView result;

    private View.OnClickListener onclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = calculator.getText().toString();
            /**
             * 如果在文本框中显示的是 error 时，if 条件触发。并且将文本框清空，之后显示按过的数字或者是操作符
             */
            if (str.equals(ERROR)) {
                str = "";
            }
            /**
             * 添加数字到文本中
             */
            if (v == num0) {
                str += "0";
            }
            if (v == num1) {
                str += "1";
            }
            if (v == num2) {
                str += "2";
            }
            if (v == num3) {
                str += "3";
            }
            if (v == num4) {
                str += "4";
            }
            if (v == num5) {
                str += "5";
            }
            if (v == num6) {
                str += "6";
            }
            if (v == num7) {
                str += "7";
            }
            if (v == num8) {
                str += "8";
            }
            if (v == num9) {
                str += "9";
            }


            /**
             * 把操作符显示到文本中
             */
            if (v == left) {
                str += LEFT;
            }
            if (v == right) {
                str += RIGHT;
            }
            if (v == div) {
                str += DIV;
            }
            if (v == mod) {
                str += MOD;
            }
            if (v == mul) {
                str += MUL;
            }
            /**
             * div 除，mod 取余， mul 乘，sub 减，add 加, point 小数点
             */
            if (v == sub) {
                str += SUB;
            }
            if (v == add) {
                str += ADD;
            }
            if (v == point) {
                str += ".";
            }
            calculator.setText(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 实例化
         */
        calculator = (EditText) findViewById(R.id.calculator);
        /**
         * 设置光标的位置
         */
        calculator.setSelection(calculator.getText().length());
        /**
         * 当文本内容发生改变时，对光标进行移动
         */
        calculator.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                calculator.setSelection(calculator.getText().length());
            }
        });
        result = (TextView) findViewById(R.id.show_result);
        num0 = (Button) findViewById(R.id.num0);
        num1 = (Button) findViewById(R.id.num1);
        num2 = (Button) findViewById(R.id.num2);
        num3 = (Button) findViewById(R.id.num3);
        num4 = (Button) findViewById(R.id.num4);
        num5 = (Button) findViewById(R.id.num5);
        num6 = (Button) findViewById(R.id.num6);
        num7 = (Button) findViewById(R.id.num7);
        num8 = (Button) findViewById(R.id.num8);
        num9 = (Button) findViewById(R.id.num9);

        /**
         * left, right, div, mod, mul, sub, add, point;
         */
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        div = (Button) findViewById(R.id.div);
        mul = (Button) findViewById(R.id.mul);
        sub = (Button) findViewById(R.id.sub);
        add = (Button) findViewById(R.id.add);
        point = (Button) findViewById(R.id.point);

        /**
         * 添加监听器
         */
        num0.setOnClickListener(onclickListener);
        num1.setOnClickListener(onclickListener);
        num2.setOnClickListener(onclickListener);
        num3.setOnClickListener(onclickListener);
        num4.setOnClickListener(onclickListener);
        num5.setOnClickListener(onclickListener);
        num6.setOnClickListener(onclickListener);
        num7.setOnClickListener(onclickListener);
        num8.setOnClickListener(onclickListener);
        num9.setOnClickListener(onclickListener);


        left.setOnClickListener(onclickListener);
        right.setOnClickListener(onclickListener);
        div.setOnClickListener(onclickListener);
        mul.setOnClickListener(onclickListener);
        sub.setOnClickListener(onclickListener);
        add.setOnClickListener(onclickListener);
        point.setOnClickListener(onclickListener);
    }

    /**
     * 点击等号触发的事件
     *
     * @param v
     */
    public void equal(View v) {
        String string = calculator.getText().toString();
        /**
         * 当文本框内容为空时，点击等号没有响应。
         */
        if (string.length() == 0) {
            return;
        } else {
            Calculator cal = new Calculator();
            String lastOp = string.substring(string.length() - 1, string.length());
            if (lastOp.equals(ADD) || lastOp.equals(SUB) || lastOp.equals(MUL)
                    || lastOp.equals(DIV) || lastOp.equals(LEFT)) {
                calculator.setText("error");
            } else {
                try {
                    double resultShow = cal.eval(string);
                    String str = "";
                    if (resultShow > 1) {
                        str = new DecimalFormat("#.0000000").format(resultShow);
                    } else {
                        str = new DecimalFormat("0.0000000").format(resultShow);
                    }
                    result.setText(str);
                } catch (Exception e) {
                    calculator.setText(ERROR);
                }
            }
        }
    }

    /**
     * 点击ac触发的事件，功能是清零工作
     * 清空操作：对文本框设置为空，显示结果的 TextView 设置为 0。
     *
     * @param v
     */
    public void ac(View v) {
        calculator.setText("");
        result.setText("0");
    }

    /**
     * 计算器实现退格处理
     *
     * @param v
     */
    public void quit(View v) {
        String string = calculator.getText().toString();
        /**
         * 当 string.length() == 0 || string.length() - 1 == 0 时，设置文本框的内容为空；
         * 否则，就会对文本框的内容进行截取子串。
         */
        if (string.length() == 0 || string.length() - 1 == 0) {
            calculator.setText("");
        } else {
            string = string.substring(0, string.length() - 1);
            calculator.setText(string);
        }
    }
}
