# 図形数当てゲーム

4種類の図形の数を当てるゲームです。

１０問出題されます。

![GOMCAM 20230125_2223450971](https://user-images.githubusercontent.com/34999008/214575788-11b729b8-530d-4282-b96b-d57b21ec4ce5.gif)


パネルは３×５マスで分かれており、１マスごとに何も置かないまたは●、■、▲、＋の図形をランダムで表示されます。


# 問題の種類

- 「●●はいくつですか？」 

- 「●●と△△を足して、いくつですか？」 

- 「●●から△△を引いて、いくつですか？」 

の三種類ある。どれかの問題が出題されます。プレイ回数ごとに、出題される問題が変化します。

# 出題される図形の追加・削除

2ステップで図形の追加・削除ができます

1. ShapeMap.java  ShapeMap()のメソッドを編集する

   例）tmp_shapeMap.put(4,"star");の行を追加する。


2. imageフォルダに写真を追加する

   例）imageフォルダ にstarのpng写真 4.pngを追加する

※shapeMapのKeyは0からの連番にする必要があります。

# 環境

java言語で開発しました。eclipse IDE環境でコードを書きました。
Intellij IDEAにはeclipseプロジェクトをインポートする機能があります。
Intellij IDEAをご利用の方もご自身のIDEAから実行することができます。

# 実行方法

 - Shapegameプロジェクトフォルダをご自身のPCでインポートし、実行
 - Shapegame.jarを実行する (java-jdk-18,java-jdk-19で実行確認済み)
 
# 著者

github-username : perugo
