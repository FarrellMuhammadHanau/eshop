# Module 1

## Reflection 1
Saat membuat fitur-fitur tersebut, saya telah membuat code yang jauh lebih _clean_ dibandingkan sebelumnya dimana saya menggunakan penamaan yang lebih mudah dipahami disertai dengan komen yang tidak terlalu berlebihan. Selain itu, saya juga telah menghindari fungsi yang memiliki return value null agar code menjadi lebih aman. Meskipun demikian, komen pada kode saya dapat dikatakan terlalu sedikit dan terkadang tidak cukup untuk menjelaskan beberapa bagian kode.

## Reflection 2
1. Setelah membuat unit test saya merasa cukup yakin bahwa code saya akan lebih aman dari error dibandingkan sebelumnya. Selain itu, dengan adanya unit test, lebih mudah bagi saya untuk melakukan debugging untuk mengecek error apa yang menyebabkan program saya gagal. Menurut saya, unit test harus diterapkan di setiap fungsi atau method terutama fungsi atau method yang kompleks. Untuk memastikan unit test cukup untuk memverifikasi code kita, kita harus memiliki code coverage yang cukup tinggi. Meskipun demikian, memiliki code coverage 100% tidak memastikan code kita tidak memiliki _bug_ atau _error_ karena meskipun semua bagian code dilakukan test, belum berarti test tersebut dapat mengecek kasus-kasus extreme.

2. Menurut saya, pembuatan class functional test tersebut dapat mengurangi kualitas kode karena akan menyebabkan adanya redundansi kode. Hal ini dikarenakan untuk mengecek seberapa banyak produk yang telah dibuat, kita harus melakukan CreateProduct terlebih dahulu yang dimana CreateProduct sudah ada pada CreateProductFunctionalTest. Untuk itu, saya menyarankan untuk membuat functional test tersebut pada class CreateProductFunctionalTest yang sudah ada, dimana setelah dilakukan CreateProduct, kita dapat melakukan test seberapa banyak Product yang sudah dibuat

# Module 2
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
   Code quality issue yang telah saya temui adalah adanya public modifier yang tidak terpakai pada file HomePageControllerTest, ProductControllerTest. dan ProductServiceImplTest dimana strategi yang     saya gunakan adalah menghapus semua public modifier yang tidak terpakai tersebut.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
  Menurut saya CI/CD workflows yang sudah saya implementasikan sudah memenuhi definisi Continuous Integration and Continuous Deployment. Pada proyek ini, terdapat github workflows dengan nama       Continuous Integration (CI) yang berfungsi untuk melakukan test yang akan mengecek code pada proyek saya setiap dilakukan pull dan push. Selain itu, terdapat workflow ScoreCard dan SonarCloud yang berfungsi untuk mengecek code quality. Untuk Continuous Deployment disini saya menggunakan bawaan Koyeb yang akan dilakukan deployment setiap dilakukan push dan pull.

# Module 3
1) Explain what principles you apply to your project!
   Dalam project ini saya menerapkan beberapa prinsip SOLID diantaranya:
   - SRP
     Saya memisahkan Product Controller dengan Car Controller dan membuat file baru untuk Car Controller. Dengan demikian, masing-masing kelas dan file memiliki satu tujuan sendiri.
   - DIP
      Saya membuat IRepository yang akan diimplement oleh ProductRepository dan CarRepository dengan tujuan service pada projek dependen pada interface tersebut dan bukan implementasinya. Hal tersebut juga berlaku bagi Controller dan service dimana Controller dependen pada interface service
   - OCP
     Dalam proyek ini, saya mempunyai class model Entity. Kelas tersebut tertutup terhadap modifikasi dan jika kita ingin menambahkan attribute pada model Entity tersebut, kita dapat lakukan dengan meng-extend class tersebut sehingga terbuka terhadap ekstensi
   - ISP
     Saya membagi 2 jenis service yaitu service untuk mencari dan service untuk memodifikasi. Dengan demikian, saya dapat membuat service baru yang hanya bertujuan untuk mencari objek tanpa memodifikasinya.

2) Explain the advantages of applying SOLID principles to your project with examples.
   Dengan menerapkan SOLID principles, project saya terasa lebih fleksible dan modular. Misal saya ingin membuat model baru yaitu pesawat. Disini saya tidak perlu mengubah entity untuk menambahkan atribute seperti jumlah tempat duduk, dimana saya hanya perlu meng-extend entity tersebut. Selain itu, saya lebih fleksible jika ingin menentukan apakah service untuk model pesawat tersebut hanya berupa pencarian atau dapat melakukan modifikasi juga. Tidak hanya itu, dengan menerapkan DIP dan SRP, kode saya menjadi lebih fleksible tanpa adanya ketergantungan yang berlebih antar modul.

3) Explain the disadvantages of not applying SOLID principles to your project with examples.
   Jika saya tidak menerapkan SOLID principles pada proyek saya, maka code saya akan terasa lebih kaku dan rumit. Misal saya ingin memodifikasi bagian Product Controller. Maka jika tidak saya pisah, Car controller juga akan terpengaruh meskipun seharusnya tidak. Selain itu, jika saya tidak menerapkan DIP pada kode saya, maka perubahan pada repository struktur pada repositori akan merusak service dan kerusakan pada service juga akan mengakibatkan kerusakan pada controller.

# Module 4
1) Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.
   Menurut saya, TDD flow sangat berguna bagi saya. Pada TDD, saya membuat test terlebih dahulu dimana saya harus memikirkan semua kemungkinan happy dan unhappy path yang ada. Setelah membuat test case tersebut, saya membuat implementasi dari test tersebut dan pada akhirnya dilakukan refactor. Dengan cara seperti itu, saya dapat tidak perlu menyesuaikan test terhadap implementasinya. Selain itu, saya tahu kasus apa yang belum saya handle saat mengimplementasikan class.

2) You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.
   Saya telah mengimplementasikan beberapa prinsip F.I.R.S.T. pada unit test saya, dimana
   1. Fast: unit test dieksekusi cukup cepat untuk sebagian besar kasus
   2. Independent: unit test yang saya buat independen terhadap satu sama lain
   3. Repeatable: dapat dijalankan kapan saja
   4. Self Validating: Unit test saya menggunakan beberapa jenis assertion untuk mengecek apakah pass atau fail
   5. Through/Timely: Test yang saya buat mungkin mengcover happy/unhappy path
