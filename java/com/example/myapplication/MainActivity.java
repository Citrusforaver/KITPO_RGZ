package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Java.Tree.ITree;
import com.example.myapplication.Java.Tree.Tree;
import com.example.myapplication.Java.UserType.MyFactory;
import com.example.myapplication.Java.UserType.UserType;

public class MainActivity extends AppCompatActivity {
    private ITree tree;
    private int userType = 0; // поумолчанию тип Int
    private MyFactory factory = new MyFactory();



    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button acceptIntType;
    private Button acceptFractionType;
    private Button closeBtn;
    private Button printTree;
    private Button insertTree;
    private Button deleteTree;
    private Button clearTree;
    private Button changeTypeTree;

    public void createNewUserTypeDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View createNewUserTypePopupView = getLayoutInflater().inflate(R.layout.selectusertype_popup, null);
        acceptIntType = (Button) createNewUserTypePopupView.findViewById(R.id.selectIntPopup);
        acceptFractionType = (Button) createNewUserTypePopupView.findViewById(R.id.selectFractionPopup);
        closeBtn = (Button) createNewUserTypePopupView.findViewById(R.id.closePopup);

        dialogBuilder.setView(createNewUserTypePopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        acceptIntType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
                userType = 0;
                generate_tree(userType);
                dialog.dismiss();
            }
        });
        acceptFractionType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
                userType = 1;
                generate_tree(userType);
                dialog.dismiss();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void generate_tree(int user_type) {

        String type = "Int";
        if(user_type == 0){
            type = "Int";
        }
        if(user_type == 1){
            type = "Fraction";
        }
        UserType userType = factory.getBuilderByName(type);
        tree = new Tree();

        if(type == "Int"){
//            tree.insertElement(userType.create(), userType.getTypeComparator());
//            tree.insertElement(userType.create(), userType.getTypeComparator());
//            tree.insertElement(userType.create(), userType.getTypeComparator());
//            tree.insertElement(userType.create(), userType.getTypeComparator());
//            tree.insertElement(userType.create(), userType.getTypeComparator());
//            tree.insertElement(userType.create(), userType.getTypeComparator());
//            tree.insertElement(userType.create(), userType.getTypeComparator());
            tree.insertElement(1, userType.getTypeComparator());
            tree.insertElement(2, userType.getTypeComparator());
            tree.insertElement(3, userType.getTypeComparator());
            tree.insertElement(4, userType.getTypeComparator());
            tree.insertElement(5, userType.getTypeComparator());
            tree.insertElement(6, userType.getTypeComparator());
            //tree.insertElement(7, userType.getTypeComparator());
            tree.Balance();
        }
        else {
            tree.insertElement(userType.create(), userType.getTypeComparator());
            tree.insertElement(userType.create(), userType.getTypeComparator());
            tree.insertElement(userType.create(), userType.getTypeComparator());
            tree.insertElement(userType.create(), userType.getTypeComparator());
            tree.insertElement(userType.create(), userType.getTypeComparator());
            tree.insertElement(userType.create(), userType.getTypeComparator());
            tree.insertElement(userType.create(), userType.getTypeComparator());
        }
        tree.Balance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewUserTypeDialog();

        printTree = (Button) findViewById(R.id.printTree);

        printTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Элементы дерева:\n"+tree.printTreList()).setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Размер дерева:" + tree.getSize());
                alertDialog.show();
            }
        });

        insertTree = (Button) findViewById(R.id.insertTree);

        insertTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder push_question = new AlertDialog.Builder(MainActivity.this);
                push_question.setMessage("Добавить элемент в дерево?").setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (userType == 0)
                                {
                                    String type = "Int";
                                    UserType userType = factory.getBuilderByName(type);
                                    tree.insertElement(userType.create(), userType.getTypeComparator());
                                }
                                if (userType == 1)
                                {
                                    String type = "Fraction";
                                    UserType userType = factory.getBuilderByName(type);
                                    tree.insertElement(userType.create(), userType.getTypeComparator());
                                }

                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_help);

                AlertDialog questionAlertDialog = push_question.create();
                questionAlertDialog.setTitle("Размер дерева:" + tree.getSize());
                questionAlertDialog.show();
            }
        });

        deleteTree= (Button) findViewById(R.id.deleteTree);

        deleteTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                EditText elem_remove = new EditText(a_builder.getContext());
                elem_remove.setHint("Введите число");

                a_builder.setMessage("Введите значение элемента для его удаления:\n" + tree.printTreList())
                        .setView(elem_remove)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                    Integer.parseInt(elem_remove.getText().toString());

                                    if (Math.ceil(Integer.parseInt(elem_remove.getText().toString())) > 0
                                            &&
                                            !elem_remove.getText().toString().isEmpty()
                                    ) {
                                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                                        a_builder.setMessage("Элемент "
                                                        + elem_remove.getText().toString()
                                                        + " был удалён!")
                                                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                })
                                                .setIcon(android.R.drawable.ic_dialog_alert);

                                        AlertDialog alertDialog = a_builder.create();
                                        alertDialog.setTitle("Удаление элемента");
                                        alertDialog.show();

                                        tree.deleteElemByInd(Integer.parseInt(elem_remove.getText().toString()));
                                    }
                                } catch (Exception e) {

                                }
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Размер списка :" + tree.getSize());
                alertDialog.show();
            }
        });

        clearTree = (Button) findViewById(R.id.clearTree);

        clearTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
                tree.clear();
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Элементы дерева:\n").setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).setIcon(android.R.drawable.ic_dialog_info);

                AlertDialog alertDialog = a_builder.create();
                alertDialog.setTitle("Размер дерева:" + tree.getSize());
                alertDialog.show();
            }
        });

        changeTypeTree = (Button) findViewById(R.id.changeTypeTree);

        changeTypeTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }

                createNewUserTypeDialog();
            }
        });

    }
}