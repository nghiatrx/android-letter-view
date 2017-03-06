# android-letter-view

## 1. Installation

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
circleLetterView.setAnimationType(SquareLetterView.NONE_ANIMATION); // default
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
