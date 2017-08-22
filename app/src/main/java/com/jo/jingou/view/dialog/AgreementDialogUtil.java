package com.jo.jingou.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.activity.Agreement;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class AgreementDialogUtil {

    private static TextView agree_tv;
    private static TextView agree_tv11;

    public static void showDialog(Activity activity, DialogConfirm
            dialogConfirm) {

        Dialog alertDialog = new Dialog(activity, R.style.MyAgreeDialog);

        View view = View.inflate(alertDialog.getContext(), R.layout.dialog_agreement, null);
        ImageView sClose_iv = (ImageView) view.findViewById(R.id.close_iv);
//        WebView sAgree_wb = (WebView) view.findViewById(R.id.agree_wb);
        agree_tv = (TextView) view.findViewById(R.id.agree_tv);
        agree_tv11 = (TextView) view.findViewById(R.id.agree_tv11);
        RelativeLayout sDialog_agree_rl = (RelativeLayout) view.findViewById(R.id.dialog_agree_rl);
        alertDialog.setContentView(view);
        //读取本地文本
//        sAgree_wb.getSettings().setJavaScriptEnabled(true);
//        sAgree_wb.getSettings().setDefaultTextEncodingName("utf-8");
//        sAgree_wb.loadUrl("file:///android_assets/a.txt");
        agree_tv.setText("开店商户需认真阅读以下协议，确认无误再点击“同意”确认签署");
        agree_tv11.setText("一、关于本协议\n" +
                "本着平等互利、诚信合作、共谋发展的合作原则，依照中华人民共和国相关的法律法规，开店商户通过利益天下商城平台利益天下电子商务平台进行产品供应及交易服务事宜，并通过利益天下商城平台接入的第三方支付系统（支付宝、微信）办理订单支付、交易退款及结算事宜。同时，本协议也包括了协议正文及所有利益天下平台已经发布的或将来可能发布的各类规则。所有规则为协议不可分割的一部分，与协议正文具有同等法律效力。\n" +
                "三、合作内容：1、利益天下分红商城为商户提供利益天下电子商务交易平台以及必要的后台管理系统所需要的用户名和密码，同时以网上展卖的形式帮助开店商户实现网上渠道的销售运作方式。2、开店商户通过利益天下商城平台的平台发布、展示、销售产品以及处理产品的相关交易事宜。\n" +
                "四、开店商户入驻商城须提交的证明文件：1、营业执照复印件，组织机构代码证复印件(三证合一不提供)，企业税务登记证复印件（国税与地税）(三证合一不提供）2、法定代表人身份证复印件3、企业持正规品牌授权书4、商标权证书复印件或者专利权证书等知识产权证明复印件，并出具允许利益天下商城平台网站销售该商品的授权书，并签字盖章。\n" +
                "五、双方权利与义务：1、利益天下商城平台权利与义务：统一管理商品信息并对其进行监督监管，为商户提供适当培训，保证系统正常运行，出现故障时应在第一时间恢复平台运行，帮助商户做好数据保存工作。2、开店商户权利与义务：自主管理商品信息，遵守相关法律，对商品信息和质量负责，及时发货，处理退货订单不得拖延，不得损害平台利益\n" +
                "六、消费者保护：利益天下平台商城将严格保护消费者的合法权益，若开店商户违反本协议或侵犯消费者合法权益，商城将采取相应措施维护消费者权益，情节严重者，将停止合作并追究其法律责任。\n" +
                "七、销售费用负担：1、开店商户在商城平台上销售货物产生的一切费用，如运输费、包装费等均由开店商户承担。开店商户应将在利益天下商城平台的平台系统上售出的产品直接或委托送货到买家指定收货处，由收货人员验收并签字确认。2、如开店商户委托利益天下商城平台按照要求完成产品的图片拍摄及制作产品技术资料，品牌策划、品牌设计及推广，开店商户需支付给利益天下商城平台一定的服务费，并在服务期间内，做好配合工作。视具体服务产品情况收取费用。\n" +
                "八、价格条款：1、若因市场因素或开店商户产品价格调整，开店商户应提前 3 个工作日通知利益天下商城平台，否则利益天下商城平台将不对因价格变化带来的损失承担责任。2、开店商户向利益天下商城平台所供商品未经利益天下平台同意不得以低于利益天下平台供与其他平台，一经发现利益天下商城平台有权立即终止合作。\n" +
                "九、销售后结算与费用扣除：1、结算方式及周期：按照利益天下平台客户确认收货之日进行销售货款结算。利益天下平台收取总销售额的20%作为分红和平台服务费。\n" +
                "2、开店商户委托利益天下商城平台通过平台签订的第三方支付系统结算货款，交易成功后货款打入利益天下商城平台账户，并由利益天下商城平台收到货款后将货款转入开店商户账户。\n" +
                "十七、 特别告知的注意条款：利益天下电子商务平台仅作为用户物色交易对象，就物品和服务的交易进行协商，以及获取各类与交易相关的服务的信息。利益天下分红商城有权对协议进行不定期修订更新。修改后的协议一经公布即有效替代原有协议，新协议公布时会以适当方式通知用户。\n" +
                "\n" + "以上协议条款最终解释权归利益天下分红商城所有");


        sDialog_agree_rl.setOnClickListener(new MyAgreementDialogClickListener(activity, alertDialog, dialogConfirm));
        sClose_iv.setOnClickListener(new MyAgreementDialogClickListener(activity, alertDialog, dialogConfirm));

        if (!activity.isFinishing()) {
            alertDialog.show();
        }
    }

    /**
     * 自定义dialog  的 点击事件
     */
    private static class MyAgreementDialogClickListener implements View.OnClickListener {

        private Activity activity;
        private DialogConfirm dialogConfirm;
        private Dialog alertDialog;

        public MyAgreementDialogClickListener(Activity activity, Dialog alertDialog, DialogConfirm dialogConfirm) {
            this.activity = activity;
            this.dialogConfirm = dialogConfirm;
            this.alertDialog = alertDialog;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_agree_rl:  //确定

                    if (!activity.isFinishing()) {
                        alertDialog.dismiss();
                    }
                    break;
                case R.id.close_iv:  //取消

                    if (!activity.isFinishing()) {
                        alertDialog.dismiss();
                    }
                    activity.finish();
                    break;
            }
        }
    }

    public interface DialogConfirm {
        void confirm();

    }

}
