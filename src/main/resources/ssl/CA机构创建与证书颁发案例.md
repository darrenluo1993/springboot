#### 1、服务器端密钥
```sh
# 生成服务器端私钥
openssl genrsa -out server.key 2048
# 生成服务器端公钥
openssl rsa -in server.key -pubout -out server.pem
```
#### 2、客户端密钥
```sh
# 生成客户端私钥
openssl genrsa -out client.key 2048
# 生成客户端公钥
openssl rsa -in client.key -pubout -out client.pem
```
#### 3、CA机构密钥（这一步是CA机构操作，我们自己代劳而已）
```sh
# 生成 CA 私钥
openssl genrsa -out ca.key 2048
# X.509证书签署请求管理。
openssl req -new -key ca.key -out ca.csr
# X.509证书数据管理。
openssl x509 -req -in ca.csr -signkey ca.key -out ca.crt -days 3650
```
```txt
# 颁发者的信息，即CA机构的信息
Identity: Root CA
Subject Name
C (Country): CN
ST (State): Hunan
L (Locality): ChangSha
O (Organization): Individual
OU (Organizational Unit): Darren Luo
CN (Common Name): LOCALHOST
EMAIL (Email Address): darrenluo1993@163.com
```
#### 4、颁发证书（这一步是CA机构操作，我们自己代劳而已）
##### 4.1、颁发服务端证书
```sh
# 服务器端需要向 CA 机构申请签名证书
openssl req -new -key server.key -out server.csr
# 向 CA 机构申请证书，签名过程需要 CA 的证书和私钥参与，最终颁发一个带有 CA 签名的证书
openssl x509 -req -CA ca.crt -CAkey ca.key -CAcreateserial -in server.csr -out server.crt -days 3650
```
```txt
# 使用者的信息，即自己公司的信息（服务端）
Identity: Server Certificate
Subject Name
C (Country): CN
ST (State): Hunan
L (Locality): ChangSha
O (Organization): Individual
OU (Organizational Unit): Darren Luo
CN (Common Name): LOCALHOST #需与CA保持一致
EMAIL (Email Address): darrenluo1993@163.com
```
##### 4.2、颁发客户端证书
```sh
# client 端
openssl req -new -key client.key -out client.csr
# client 端到 CA 签名
openssl x509 -req -CA ca.crt -CAkey ca.key -CAcreateserial -in client.csr -out client.crt -days 3650
```
```txt
# 使用者的信息，即自己公司的信息（客户端）
Identity: Client Certificate
Subject Name
C (Country): CN
ST (State): Hunan
L (Locality): ChangSha
O (Organization): Individual
OU (Organizational Unit): Darren Luo
CN (Common Name): LOCALHOST #需与CA保持一致
EMAIL (Email Address): darrenluo1993@163.com
```
#### 5、生成pkcs12格式证书（这一步也不是必须，只是用在某些场合方便而已）
```sh
# 生成pkcs12服务器证书 server.p12，需要输入2次密码，设置密码为abcd1234
openssl pkcs12 -export -in server.crt -inkey server.key -out server.p12 -name server -CAfile ca.crt -caname ca
# 生成pkcs12客户端证书 client.p12，需要输入2次密码，设置密码为abcd1234
openssl pkcs12 -export -in client.crt -inkey client.key -out client.p12 -name client -CAfile ca.crt -caname ca
```
#### 6、服务器端，将客户端证书导入为受信任的证书
```sh
# 服务器端，将客户端证书导入为受信任的证书
# 如需信任多个客户端证书，重复执行以下语句导入多个即可，注意保证alias唯一
keytool -import -trustcacerts -file client.crt -alias client -storepass abcd1234 -keystore client.jks
```