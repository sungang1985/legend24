package com.nari.sungang.legendof24.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bestcode.mathparser.IMathParser;
import com.bestcode.mathparser.MathParserFactory;
import com.nari.sungang.legendof24.R;
import com.nari.sungang.legendof24.adapter.ImageViewAdapter;
import com.nari.sungang.legendof24.adapter.OperationAdapter;
import com.nari.sungang.legendof24.adapter.RecyclerViewAdapter;
import com.nari.sungang.legendof24.model.Operation;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sungang on 2016/1/1.
 */
public class GameActivity extends AppCompatActivity {
    private static int[] operations = {
            R.drawable.plus, R.drawable.minus, R.drawable.multiply, R.drawable.division, R.drawable.parentheses,
            R.drawable.power, R.drawable.percent, R.drawable.factorial, R.drawable.braces, R.drawable.brackets};

    private static List<Operation> operationList = new ArrayList<Operation>();

    private LinearLayout gameLinerLayout;
    private GridView gridView;
    private MaterialEditText materialEditText;
    private ImageButton backspace;
    private ImageButton clear;
    private RecyclerView recyclerView;
    private Button confirm;

    static {
        Operation plus = new Operation(R.drawable.plus, "+");
        operationList.add(plus);

        Operation minus = new Operation(R.drawable.minus, "-");
        operationList.add(minus);

        Operation multiply = new Operation(R.drawable.multiply, "*");
        operationList.add(multiply);

        Operation division = new Operation(R.drawable.division, "/");
        operationList.add(division);

        Operation parentheses = new Operation(R.drawable.parentheses, "()");
        operationList.add(parentheses);

        Operation power = new Operation(R.drawable.power, "2");
        operationList.add(power);

        Operation percent = new Operation(R.drawable.percent, "%");
        operationList.add(percent);

        Operation factorial = new Operation(R.drawable.factorial, "!");
        operationList.add(factorial);

        Operation braces = new Operation(R.drawable.braces, "{}");
        operationList.add(braces);

        Operation brackets = new Operation(R.drawable.brackets, "[]");
        operationList.add(brackets);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();
        initRecyclerView();

    }

    private void initView() {
        gameLinerLayout = (LinearLayout) findViewById(R.id.gameLinerLayout);
        gridView = (GridView) findViewById(R.id.imageGridView);
        materialEditText = (MaterialEditText) findViewById(R.id.materialEditText);
        backspace = (ImageButton) findViewById(R.id.backspace);
        clear = (ImageButton) findViewById(R.id.clear);
        confirm = (Button) findViewById(R.id.confirm);

        ViewTreeObserver viewTreeObserver = gameLinerLayout.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                gameLinerLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                gridView.setAdapter(new ImageViewAdapter(GameActivity.this, gameLinerLayout.getWidth(), gameLinerLayout.getHeight()));
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String value = (String) ((ImageView) v).getTag();
                materialEditText.setText((materialEditText.getText() == null ? "" : materialEditText.getText()) + value);
            }
        });

        materialEditText.setCursorVisible(false);//隐藏光标
        materialEditText.setFocusable(false);//失去焦点
        materialEditText.setFocusableInTouchMode(false);//虚拟键盘隐藏

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = materialEditText.getText().toString();
                if (!"".equals(text)) {
                    text = text.substring(0, text.length() - 1);
                    materialEditText.setText(text);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialEditText.setText("");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = materialEditText.getText().toString();
                if ("".equals(text)) {
                    Toast.makeText(GameActivity.this, "请点击卡牌和运算符号计算得到24", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        IMathParser parser = MathParserFactory.create();
                        parser.setExpression(text);
                        int value = (int) parser.getValue();
                        if (24 == value) {
                            Toast.makeText(GameActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GameActivity.this, "失败，请重新组合", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                        Toast.makeText(GameActivity.this, "失败，请重新组合", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(operationList, GameActivity.this);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                materialEditText.setText((materialEditText.getText() == null ? "" : materialEditText.getText()) + operationList.get(position).getSymbol());
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        /*OperationAdapter operationAdapter = new OperationAdapter(this, R.layout.operation_item, operationList);
        TwoWayView recyclerView = (TwoWayView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(operationAdapter);

        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Operation operation = operationList.get(position);
                materialEditText.setText((materialEditText.getText() == null ? "" : materialEditText.getText()) + operation.getSymbol());
            }
        })*/
        ;
    }

}
