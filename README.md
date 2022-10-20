# CS211 Project - KU ร้องเรียน
## รายชื่อสมาชิก
- นายกัญจน์ ศรีประไพ 6410400993<br>
- นายธนภัทร เชื้อโตหลวง 6410401060<br>
- นางสาวดุจรวี เหล่าอยู่คง 6410406584<br>
- นายธีรณัฏฐ์ สุธนรักษ์ 6410406673<br>

## วิธีการติดตั้งโปรแกรม
ให้ผู้ใช้ [ดาวน์โหลดไฟล์โปรแกรม]() <br>
- ### สำหรับผู้ใช้ Windows
&nbsp; &nbsp; &nbsp; &nbsp; `*****ไว้มาเขียน`
- ### สำหรับผู้ใช้ macOS
&nbsp; &nbsp; &nbsp; &nbsp; `*****ไว้มาเขียน`

## การวางโครงสร้างไฟล์

- โครงสร้างภาพรวม
  ```
    root
    └───data
    │   │  ...
    │
    └───src
        │  ...
  ```

- ข้อมูลที่เก็บในฐานข้อมูล
  ```
  data
  └───image
  │    │  ...
  │
  │   *.csv
  ```

- รูปภาพโปรไฟล์และรูปภาพประกอบการร้องเรียน<br>ที่อัพโหลดโดยผู้ใช้
  ```
  image
  └───account
  │     │  ...
  │
  └───complaint
        │  ...
  ```

- source code
  ```
  src
  └───main
      └───java
      │   └───module-info.java
      │   │
      │   └───ku/cs
      │        └───controllers
      │        │     │  *.java 
      │        │
      │        └───models
      │        │     └───accounts
      │        │     │    │ *.java
      │        │     │    
      │        │     └───category
      │        │     │    │ *.java
      │        │     │    
      │        │     └───complaints
      │        │     │    │ *.java
      │        │     │  
      │        │     └───reports
      │        │     │    │ *.java
      │        │     │  
      │        │     └───units
      │        │          │ *.java
      │        │       
      │        └───services
      │        │     └───comparator
      │        │     │    │ *.java
      │        │     │  
      │        │     └───datasource
      │        │     │    │ *.java
      │        │     │  
      │        │     └───filter
      │        │     │    │ *.java
      │        │     │  
      │        │     └───styles
      │        │     │    │ *.java
      │        │
      │        └───util
      │        │     │  *.java
      │        │
      │        └───`outsource package`
      │        │     │  ...  
      │        │     
      │        └───Main.java
      │        └───ProjectApplication.java
      │
      └───resources/ku/cs
          └───page
          │    │   *.fxml
          │
          └───fonts
          │    │   *.ttf
          │
          └───image
          │    │   *
          │
          └───styles
               │   *.css
   ```
## สรุปสิ่งที่พัฒนาแต่ละครั้งที่นำเสนอความก้าวหน้าของระบบ
* ### ครั้งที่ 1 (11 Aug 2022)
  - โครงร่างของโปรแกรมทั้งหมด
  - ระบบการอ่านข้อมูลเบื้องต้น (Hardcode)
* ### ครั้งที่ 2 (9 Sep 2022)
  - ระบบการอ่านและเขียนข้อมูลไฟล์ csv ในโปรแกรม
  - ระบบการสมัครเข้าใช้งานและการเข้าสู่ระบบ
  - สามารถแสดง font เพิ่มเติมได้อย่างถูกต้อง
* ### ครั้งที่ 3 (30 Sep 2022)
  - ระบบการร้องเรียน 
      - สามารถร้องเรียนในหมวดหมู่ที่แตกต่างกัน ใส่ข้อมูล และอัพโหลดรูปประกอบตามคุณลักษณะของหมวดหมู่ได้
      - สามารถแสดงรายละเอียดคุณลักษณะตามหมวดหมู่ของแต่ละเรื่องร้องเรียนในรายการเรื่องร้องเรียนได้อย่างถูกต้อง
  - การ login แยกส่วนการแสดงเมนูและ feature การใช้งานของ ผู้ใช้ เจ้าหน้าที่ และผู้ดูแลระบบ
  - ผู้ใช้สามารถรายงานเรื่องร้องเรียนที่ไม่เหมาะสม และผู้เสนอเรื่องร้องเรียนที่มีพฤติกรรมไม่เหมาะสมได้
  - การแก้ไขข้อมูลบัญชีผู้ใช้และการเปลี่ยนรูปโปรไฟล์

