package com.the3dsandwich.haileyandweiweibackend.controller;

import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRq;
import com.the3dsandwich.haileyandweiweibackend.controller.bean.SignupWeddingRs;
import com.the3dsandwich.haileyandweiweibackend.service.GuestService;
import com.the3dsandwich.haileyandweiweibackend.service.HWEmailService;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestBo;
import com.the3dsandwich.haileyandweiweibackend.service.bean.GuestTransportationEnum;
import com.the3dsandwich.haileyandweiweibackend.service.bean.SendEmailInput;
import com.the3dsandwich.haileyandweiweibackend.service.bean.SendEmailOutput;
import com.the3dsandwich.haileyandweiweibackend.utils.HWStringUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@RestController
public class WeddingController {

    private static final String EMAIL_SUBJECT = "肇倫與善維的婚宴｜表單回覆";

    @Autowired
    private HWEmailService hwEmailService;
    @Autowired
    private GuestService guestService;

    @PostMapping("/signup")
    public SignupWeddingRs signupWedding(@RequestBody SignupWeddingRq request) {
        // sign up guest
        addGuest(request);

        // sign up companion if exist
        if (BooleanUtils.isTrue(request.isBringCompanion())) {
            log.debug("{} brings companion {}", request.getName(), request.getCompanionName());
            addCompanion(request);
        }

        // send email to guest
        SendEmailOutput sendEmailOutput = sendEmail(request);

        // build API response
        return SignupWeddingRs.builder()
                              .message(sendEmailOutput.getStatus())
                              .build();
    }

    private void addGuest(SignupWeddingRq request) {
        guestService.addGuest(request);
    }

    private void addCompanion(SignupWeddingRq request) {
        GuestBo companion = GuestBo.builder()
                                   .name(request.getCompanionName())
                                   .vegetarian(request.isCompanionVegetarian())
                                   .friendOf(request.getName())
                                   .email(request.getEmail())
                                   .nickname(HWStringUtils.format("{}'s companion", request.getName()))
                                   .isPhysicalInvitation(false)
                                   .tags(List.of(GuestService.GUEST))
                                   .comments(HWStringUtils.format("{}'s companion", request.getName()))
                                   .build();
        companion.setTransportation(request.getTransportation());
        guestService.addGuest(companion);
    }

    private SendEmailOutput sendEmail(SignupWeddingRq request) {
        SendEmailInput sendEmailInput = SendEmailInput.builder()
                                                      .emailSubject(EMAIL_SUBJECT)
                                                      .emailFromName(From.of(request.getFriendOf())
                                                                         .getFromName())
                                                      .emailFromAddress(From.of(request.getFriendOf())
                                                                            .getFromEmailAddress())
                                                      .emailToAddress(HWStringUtils.trimToEmpty(request.getEmail()))
                                                      .emailContentHtml(buildContentHtml(request))
                                                      .build();
        return hwEmailService.sendEmail(sendEmailInput);
    }

    private String buildContentHtml(SignupWeddingRq request) {
        String contentHtml = HWStringUtils.format("""
                                                          <!DOCTYPE html>
                                                          <html lang="zh_TW">
                                                            <head>
                                                              <meta charset="UTF-8" />
                                                              <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                                                              <title>Welcome to Our Wedding! 肇倫與善維的婚宴</title>
                                                            </head>
                                                            <body style="width: 480px; margin: 0 auto; text-align:center">
                                                              <h2>感謝您回覆肇倫與善維的婚宴表單！</h2>
                                                              親愛的 {} 您好，<br />
                                                              謝謝您參加肇倫與善維的婚禮，以下是您的回覆內容，請再確認是否有需要更改，若需要更改請聯絡肇倫或善維<br /><br />
                                                              <b>親友</b><br />
                                                              {}的親友<br /><br />
                                                              <b>電子郵件</b><br />
                                                              {}<br /><br />
                                                              <b>素食</b><br />
                                                              {}<br /><br />
                                                              <b>實體喜帖</b><br />
                                                              {}<br /><br />
                                                              <b>交通方式</b><br />
                                                              {}<br /><br />
                                                              <b>留言</b><br />
                                                              {}<br /><br />
                                                              <b>是否攜伴</b><br />
                                                              {}<br /><br />
                                                              以下是婚宴當天資訊：<br /><br />
                                                              <b>婚宴日期 | Date</b><br />
                                                              2024 年 12 月 01 日 <br />
                                                              12 : 00 進場 12 : 30 開席<br /><br />
                                                              <b>婚宴地址 | Address</b><br />
                                                              台北寒舍艾美酒店<br />
                                                              台北市信義區松仁路38號二樓<br />
                                                              <a
                                                                href="https://maps.app.goo.gl/cHEWEB56d5x1qjXv6"
                                                                target="_blank"
                                                                className="underline"
                                                                >(Google 地圖)
                                                              </a><br /><br />
                                                              <b>電子喜帖 | Invitation</b><br />
                                                              <a
                                                                href="https://wedding.haileyweiwei.com/signup"
                                                                target="_blank"
                                                                className="underline"
                                                              >https://wedding.haileyweiwei.com</a><br /><br />
                                                              誠心期待您的蒞臨！<br />
                                                              ——肇倫和善維<br /><br />
                                                              <img
                                                                src="https://wedding.haileyweiwei.com/_next/image?url=/_next/static/media/WANG0986_%E6%94%BE%E5%A4%A724%E5%AF%B8.db3b87a8.jpg&w=1080&q=75"
                                                                alt="img"
                                                                width="100%"
                                                              />
                                                            </body>
                                                          </html>
                                                              """,
                                                  request.getName(),
                                                  From.of(request.getFriendOf())
                                                      .getFromName(),
                                                  HWStringUtils.trimToEmpty(request.getEmail()),
                                                  BooleanUtils.toString(request.isVegetarian(), "是", "否"),
                                                  BooleanUtils.toString(request.getIsPhysicalInvitation(),
                                                                        HWStringUtils.format("是（地址：{}）",
                                                                                             HWStringUtils.trimToEmpty(
                                                                                                     request.getPhysicalAddress())),
                                                                        "否"),
                                                  GuestTransportationEnum.of(request.getTransportation())
                                                                         .getName(),
                                                  HWStringUtils.defaultIfEmpty(HWStringUtils.trimToEmpty(request.getComments()),
                                                                               "（無）"),
                                                  BooleanUtils.toString(request.isBringCompanion(),
                                                                        HWStringUtils.format("是（{}，{}素食）",
                                                                                             HWStringUtils.trimToEmpty(
                                                                                                     request.getCompanionName()),
                                                                                             BooleanUtils.toString(
                                                                                                     request.isCompanionVegetarian(),
                                                                                                     "",
                                                                                                     "非")),
                                                                        "否"));

        log.debug("email content:\n{}", contentHtml);

        return contentHtml;
    }

    @Getter
    private enum From {

        WEIWEI("Wei-Wei", "葉善維 Wei-Wei", "weiwei@wedding.haileyweiwei.com"),
        HAILEY("Hailey", "許肇倫 Hailey", "hailey@wedding.haileyweiwei.com"),
        BOL("BOL", "肇倫和善維 Hailey and Wei-Wei", "haileyandweiwei@wedding.haileyweiwei.com"),
        ;

        private final String code;
        private final String fromName;
        private final String fromEmailAddress;

        From(String code, String name, String address) {
            this.code = code;
            this.fromName = name;
            this.fromEmailAddress = address;
        }

        public static From of(String friendOf) {
            return switch (friendOf) {
                case "Wei-Wei" -> WEIWEI;
                case "Hailey" -> HAILEY;
                case "BOL" -> BOL;
                default -> throw new IllegalStateException("Unexpected value: " + friendOf);
            };
        }
    }

}
