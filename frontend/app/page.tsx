import SignupForm from "@/components/SignupForm";
import Hr from "./component/Hr";
import {
  WANG0705,
  WANG0795_謝卡2,
  WANG0882_桌框,
  WANG0986_放大24寸,
} from "./images/images";
import TopNav from "@/components/TopNav";

export default function Home() {
  return (
    <>
      <TopNav showSignupForm />
      <div className="text-center flex flex-col items-center mb-40">
        <WANG0705 />
        <br />
        <h1 className="text-red-300 font-extrabold text-xl">婚禮邀請函</h1>
        <br />
        Welcome to our wedding
        <Hr />
        人生是一場以愛之名的相聚
        <br />
        我們是彼此親自挑選的家人
        <br />
        希望我們有幸
        <br />
        邀請收到喜帖的每一個你
        <br />
        見證我們此生最重要的決定
        <br />
        <br />
        <SignupForm />
        <br />
        <br />
        <WANG0795_謝卡2 />
        <br />
        <br />
        人的一生有三萬多天
        <br />
        很開心這一天
        <br />
        你專為我們而來
        <Hr />
        <div className="text-left w-72">
          <b>日期</b>：2024 年 12 月 01 日
          <br />
          <b>時間</b>：12:00 入席
          <br />
          <b>地點</b>：台北寒舍艾美酒店 二樓宴會廳
          <br />
          <a
            href="https://maps.app.goo.gl/cHEWEB56d5x1qjXv6"
            target="_blank"
            className="underline"
          >
            (Google 地圖)
          </a>
        </div>
        <br />
        <br />
        <WANG0882_桌框 />
        <br />
        <br />
        致珍貴的你們
        <br />
        我們決定
        <br />
        在 2024 年 12 月 01 日這一天
        <br />
        向彼此立下永恆的盟約
        <br />
        攜手度過往後無數個日夜
        <br />
        <Hr />
        期待我們在這一天重聚
        <br />
        不僅是一個婚禮 更像是一個 party
        <br />
        找個機會與久未見面的朋友們一起
        <br />
        談天說地 分享喜悅
        <br />
        更想與你分享我們的故事
        <br />
        <br />
        <SignupForm />
        <Hr />
        <div className="text-left w-72">
          <b>溫馨提醒</b>
          <ul>
            <li>
              {"> "}
              自行開車、騎車皆可免費消磁，請直接停進地下室，婚宴結束後，將磁卡拿給酒店工作人員消磁即可
            </li>
            <li>
              {"> "}酒店不收開瓶費，但若有嘔吐，酒店會酌收清潔費
              $8,000，請各位理性飲酒，感恩
            </li>
            <li>
              {"> "}12:00-12:30
              開席前，宴會廳外面有餐前雞尾酒會，歡迎各位盡情享用
            </li>
          </ul>
        </div>
        <br />
        <br />
        <WANG0986_放大24寸 />
        <Hr />
        <b>婚宴日期 | Date</b>
        2024 年 12 月 01 日
        <br />
        12:00 入席
        <br />
        <br />
        <b>婚宴地址 | Address</b>
        台北寒舍艾美酒店
        <br />
        台北市信義區松仁路38號二樓
        <a
          href="https://maps.app.goo.gl/cHEWEB56d5x1qjXv6"
          target="_blank"
          className="underline"
        >
          (Google 地圖)
        </a>
        <Hr />
        <iframe
          title="le meridien"
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d57843.81565744926!2d121.5193902!3d25.025979700000008!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abba50d70b7b%3A0x313d65ce289c8267!2z5Y-w5YyX5a-S6IiN6Im-576O6YWS5bqX!5e0!3m2!1szh-TW!2stw!4v1714287626753!5m2!1szh-TW!2stw"
          className="w-full md:w-1/3 aspect-square"
          loading="lazy"
        />
        <Hr />
        <b>「感謝您/不遠千里/見證我們的愛情」</b>
        <br />
        2024.12.01
        <br />
        我們，婚禮見
        <div className="mt-8">
          <SignupForm />
        </div>
      </div>
    </>
  );
}
