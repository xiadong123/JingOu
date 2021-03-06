package com.jo.jingou.activity;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;

/**
 * 作者：$夏冬
 * 时间: 2017/7/14 15:19
 */

public class Agreement extends MyBaseActivity {


    private TextView agreement_tv;
    private TextView agreement_tv1;
    private TextView agreement_tv2;
    @Override
    public void initData() {
        layoutId = R.layout.agreement;
    }

    @Override
    public void findViews() {

        agreement_tv = (TextView) findViewById(R.id.agreement_tv);
        agreement_tv1 = (TextView) findViewById(R.id.agreement_tv1);
        agreement_tv2 = (TextView) findViewById(R.id.agreement_tv2);


    }


        @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        agreement_tv.setText(Html.fromHtml("<font color=\"#999999\">利益天下平台服务协议\n" ));
        agreement_tv1.setText(Html.fromHtml("提示条款\n"));
        agreement_tv2.setText(Html.fromHtml("欢迎您与利益天下平台经营者（详见定义条款）共同签署本《利益天下平台服务协议》（下称“本协议”）并使用利益天下平台服务。\n" +
                " \n" +
                "各服务条款前所列索引关键词仅为帮助您理解该条款表达的主旨之用，不影响或限制本协议条款的含义或解释。为维护您自身权益，建议您仔细阅读各条款具体表述。\n" +
                " \n" +
                "【审慎阅读】您在申请注册流程中点击同意本协议之前，应当认真阅读本协议。请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制责任的条款、法律适用和争议解决条款。免除或者限制责任的条款将以粗体下划线标识，您应重点阅读。如您对协议有任何疑问，可向利益天下平台客服咨询。\n" +
                " \n" +
                "【签约动作】当您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，即表示您已充分阅读、理解并接受本协议的全部内容，并与利益天下达成一致，成为利益天下平台“小天使”。阅读本协议的过程中，如果您不同意本协议或其中任何条款约定，您应立即停止注册程序。\n" +
                " \n" +
                "一、 定义\n" +
                " \n" +
                "利益天下平台：指包括上海静欧商贸有限公司建立的网站及客户端。\n" +
                " \n" +
                "利益天下平台服务：利益天下基于互联网，以包含利益天下平台网站、客户端等在内的各种形态（包括未来技术发展出现的新的服务形态）向您提供的各项服务。\n" +
                " \n" +
                "利益天下平台规则：包括在所有利益天下平台规则频道内已经发布及后续发布的全部规则、解读、公告等内容各类规则、实施细则、产品流程说明、公告等。\n" +
                " \n" +
                "同一用户：使用同一身份认证信息或经利益天下排查认定多个利益天下账户的实际控制人为同一人的，均视为同一用户。\n" +
                " \n" +
                "二、 协议主体\n" +
                " \n" +
                "本协议由您与利益天下平台经营者共同缔结，本协议对您与利益天下平台经营者均具有合同效力。\n" +
                " \n" +
                "三、 账户注册与使用\n" +
                " \n" +
                "3.1 您确认，在您开始注册程序使用利益天下平台服务前，您应当具备与您行为相适应的民事行为能力。若不具备，则您及您的监护人应依照法律规定承担因此而导致的一切后果。\n" +
                " \n" +
                "3.2当您按照注册页面提示填写信息、阅读并同意本协议且完成全部注册程序后，您可获得利益天下平台账户并成为利益天下平台“小天使”用户，并有权使用该账户。\n" +
                " \n" +
                "3.3为使您更好地使用利益天下平台的各项服务，保障您的账户安全，利益天下要求您完成实名认证。\n" +
                " \n" +
                "3.4您的账户为您自行设置并由您保管，利益天下任何时候均不会主动要求您提供您的账户密码。因此，建议您务必保管好您的账户。\n" +
                " \n" +
                "四、协议的变更与终止\n" +
                " \n" +
                "利益天下可根据国家法律法规变化及维护交易秩序、保护消费者权益需要，不时修改本协议、补充协议，变更后的协议、补充协议（下称“变更事项”）将通过法定程序和本协议约定的方式通知您。您有权提出反馈意见。\n" +
                " \n" +
                "五、小天使的违约及处理\n" +
                " \n" +
                "5.1违反本协议约定的视为违约。若您违反本协议，利益天下可限制您的小天使账户，同时，您需要赔偿利益天下因此遭受的损失。\n" +
                " \n" +
                "5.2因不可抗力导致的合同履行障碍、履行瑕疵、履行延后或履行内容变更等情形，利益天下并不承担违约责任。\n" +
                " \n" +
                "六、其他\n" +
                " \n" +
                "6.1本协议适用中华人民共和国大陆地区法律。\n" +
                " \n" +
                "6.2您因使用利益天下平台服务所产生及与利益天下平台服务有关的争议，由利益天下与您协商解决。协商不成时，任何一方均可向被告所在地人民法院提起诉讼。\n" +
                " \n" +
                "6.3您在注册成为小天使时，应该向利益天下提供真实有效的联系方式（包括您的电子邮件地址、联系电话、联系地址等），利益天下将向您的上述联系方式的其中之一或其中若干向您送达各类通知。对于联系方式发生变更的，您应当在变更之日起七日内告知利益天下分，不告知的，若利益天下分按照原联系方式寄发各类通知，视为有效送达。\n" +
                "6.4其他未尽事项详见《利益天下分注册协议》，本协议未尽事项或冲突事项，优先适用《利益天下分注册协议》。\n"));
    }
}
