# android-letter-view

## 1. Installation

GRADLE
```
compile 'com.github.nghiatrx:letter-view:0.0.1'
```

MAVEN
```
<dependency>
    <groupId>com.github.nghiatrx</groupId>
    <artifactId>letter-view</artifactId>
    <version>0.0.1</version>
</dependency>
```

view more: https://search.maven.org/#artifactdetails%7Ccom.github.nghiatrx%7Cletter-view%7C0.0.1%7Caar

## 2. Demo

![m1](/images/m1.gif)

## 3. Usage

### CircleLetterView

Insert below code into your layout file:

```
<org.nghiatrx.libs.letterview.CircleLetterView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/circleLetterView"/>
````

To run animation:

```
ircleLetterView circleLetterView = (CircleLetterView) view.findViewById(R.id.circleLetterView);
circleLetterView.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         circleLetterView.runSelectedAnimation();
     }
 });
```

Animation type:

```
circleLetterView.setAnimationType(SquareLetterView.ROTATE_ANIMATION); // default
```

![m1](/images/m1.gif)

```
circleLetterView.setAnimationType(SquareLetterView.NONE_ANIMATION);
```

![m2](/images/m2.gif)

Change default indicator

![m2](/images/m2.gif)

Change color

```
circleLetterView.setColor(int color)
circleLetterView.setSelectedColor(int selectedColor)
```

### SquareLetterView

```
<org.nghiatrx.libs.letterview.SquareLetterView
        android:layout_width="65dp"
        android:layout_height="65dp"
        android:id="@+id/squareLetterView"/>
```

![m4](/images/m4.png)
