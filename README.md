OUTINI-KAERO

【概要】
OUTINI-KAERO は、迷子猫の発見・情報共有を支援する Web アプリケーションです。
ユーザーはアカウント登録・ログインし、迷子猫の情報を投稿したり、位置情報を基に周辺の猫を検索したり、他のユーザーとメッセージをやり取りできます。
管理者用画面とユーザー用画面を分離し、Spring Security による認証／認可制御を実装しています。

【技術スタック】
・Java 21

・Spring Boot 3.x
	・Spring Security
	・Spring Data JPA / Hibernate

・MySQL 8.0.42
	・空間データ型（SRID 4326 / WGS84）利用

・Thymeleaf (テンプレートエンジン)

・Maven (依存管理)

・HTML / CSS / JavaScript (フロントエンド)

【主な機能】

・ユーザー機能
	・アカウント登録／ログイン／ログアウト
	・位置情報の登録・更新（緯度経度を基に検索）
	・メッセージ送受信（削除機能あり）

・迷子猫機能
	・迷子猫情報の登録・編集・削除
	・位置情報を用いた「近くの猫検索」（例：10km 以内）
	・自分の投稿は検索結果から除外

・管理者機能
	・ユーザー検索／管理
	・投稿内容の確認・削除

・認証／認可
	・@Order を用いた複数 SecurityFilterChain 設定
	・ユーザー用と管理者用のログイン画面分離

・フロントエンド
・Thymeleaf による画面描画
	・フラグメント化されたヘッダー／フッター
	・スマートフォン向けのレスポンシブデザイン対応

【セットアップ】
1.リポジトリをクローン
git clone https://github.com/runrun-day/OUTINI-KAERO.git

2.DB を作成し、application.yml または application.properties に接続情報を設定
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/outini_kaero?serverTimezone=Asia/Tokyo
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update

3.ビルド・起動
./mvnw clean install
./mvnw spring-boot:run


4.アクセス
http://localhost:8080

【今後の改善ポイント】
・迷子猫情報の画像アップロード機能
・管理者画面の UI/UX 強化
・テストコードの充実 (JUnit, Mockito)
