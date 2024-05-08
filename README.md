# 概要
SAML認証（Keycloak）x Springbootアプリケーションのデモアプリケーションです。
最低限の認証の確認のみ実装。

参考
https://www.a-frontier.jp/technology/security11/

# pom.xml
末尾にSAML関連の依存関係を追加。
Centralリポジトリの最終アップデートは2021年だったため、他のリポジトリを明示的に追加。
open-saml関連の依存はクライアントのメタデータ生成のために追加
以下URLにアプリ起動してアクセスするとメタデータを取得できる。
http://localhost:8080/saml2/service-provider-metadata/my-client

## 秘密鍵生成
openssl req -newkey rsa:2048 -nodes -keyout local.key -x509 -days 365 -out local.crt
→上記コマンドで全て未入力で生成
