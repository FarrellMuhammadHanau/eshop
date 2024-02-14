#Module 1

## Reflection 1
Saat membuat fitur-fitur tersebut, saya telah membuat code yang jauh lebih _clean_ dibandingkan sebelumnya dimana saya menggunakan penamaan yang lebih mudah dipahami disertai dengan komen yang tidak terlalu berlebihan. Selain itu, saya juga telah menghindari fungsi yang memiliki return value null agar code menjadi lebih aman. Meskipun demikian, komen pada kode saya dapat dikatakan terlalu sedikit dan terkadang tidak cukup untuk menjelaskan beberapa bagian kode.

## Reflection 2
1. Setelah membuat unit test saya merasa cukup yakin bahwa code saya akan lebih aman dari error dibandingkan sebelumnya. Selain itu, dengan adanya unit test, lebih mudah bagi saya untuk melakukan debugging untuk mengecek error apa yang menyebabkan program saya gagal. Menurut saya, unit test harus diterapkan di setiap fungsi atau method terutama fungsi atau method yang kompleks. Untuk memastikan unit test cukup untuk memverifikasi code kita, kita harus memiliki code coverage yang cukup tinggi. Meskipun demikian, memiliki code coverage 100% tidak memastikan code kita tidak memiliki _bug_ atau _error_ karena meskipun semua bagian code dilakukan test, belum berarti test tersebut dapat mengecek kasus-kasus extreme.

2. Menurut saya, pembuatan class functional test tersebut dapat mengurangi kualitas kode karena akan menyebabkan adanya redundansi kode. Hal ini dikarenakan untuk mengecek seberapa banyak produk yang telah dibuat, kita harus melakukan CreateProduct terlebih dahulu yang dimana CreateProduct sudah ada pada CreateProductFunctionalTest. Untuk itu, saya menyarankan untuk membuat functional test tersebut pada class CreateProductFunctionalTest yang sudah ada, dimana setelah dilakukan CreateProduct, kita dapat melakukan test seberapa banyak Product yang sudah dibuat

#Module 2
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
   Code quality issue yang telah saya temui adalah adanya public modifier yang tidak terpakai pada file HomePageControllerTest, ProductControllerTest. dan ProductServiceImplTest dimana strategi yang     saya gunakan adalah menghapus semua public modifier yang tidak terpakai tersebut.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
  Menurut saya CI/CD workflows yang sudah saya implementasikan sudah memenuhi definisi Continuous Integration and Continuous Deployment. Pada proyek ini, terdapat github workflows dengan nama       Continuous Integration (CI) yang berfungsi untuk melakukan test yang akan mengecek code pada proyek saya setiap dilakukan pull dan push. Selain itu, terdapat workflow ScoreCard dan SonarCloud yang berfungsi untuk mengecek code quality. Untuk Continuous Deployment disini saya menggunakan bawaan Koyeb yang akan dilakukan deployment setiap dilakukan push dan pull.
