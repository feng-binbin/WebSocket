let photoDisplayBoxWidth = document.querySelector(".photoDisplayBox").clientWidth;/*图片选择器的宽度*/
let selectorItems = document.querySelectorAll(".photoDisplayBox .selector .item");/*图片轮播器的底部选择项*/
let selectorInnerBox = document.querySelector(".photoDisplayBox .innerBox");/*图片容器*/
let selectorPhotos = document.querySelectorAll(".photoDisplayBox .innerBox a");/*所有图片的父标签*/
let currentItem = 0;/*记录当前是第几张图片*/
let currentMarginLeftWidth = 0;/*记录当前marginLeft值*/
let timeout = 2000;/*自动轮播时长*/

let autoChangeTimer = setInterval(autoChangePhoto, timeout);/*两秒钟改变一次*/
let detailChangeTimer = null;

changeItemColor();//默认第一个图片选择器为选中

/*自动轮播*/
function autoChangePhoto() {
    if (currentItem % 5 === 0) {//第一张图片直接跳转，不实现轮播
        currentItem = 0;
        currentMarginLeftWidth = 0;
        changeItemColor();
        selectorInnerBox.style.marginLeft = "0px";//直接设置margin-left的值为0px,即直接跳转到第一张图片
    } else {
        changeInnerBoxMarginLeft();//调用“走马灯式”过渡到下一张图片的方法
    }
    currentItem++;//每次自动轮播后当前图片选择项+1，用来进行下一次跳转
}

/*手动轮播*/
function selfChangePhoto() {
    if (currentMarginLeftWidth === (-currentItem * photoDisplayBoxWidth)) {//如果选择项没有改变，则依旧显示对应的选择项图片
        selectorInnerBox.style.marginLeft = (-currentItem * photoDisplayBoxWidth) + "px";
    } else {
        changeInnerBoxMarginLeft();//跳转到对应的图片
    }
}

/*核心：通过定时器改变"margin-left"属性值实现走马灯式图片轮播*/
function changeInnerBoxMarginLeft() {
    clearInterval(autoChangeTimer);//每次进行图片跳转时，先关闭自动轮播定时器，待跳转结束后再开启定时器。
    changeItemColor();//跳转到对应的图片选择题

    let newMarginLeftWidth = -currentItem * photoDisplayBoxWidth;//记录需要跳转到图片选择项的margin-left值
    let distant = currentMarginLeftWidth - newMarginLeftWidth;//记录当前的margin-left值到需要跳转的margin-left值的差值
    let index = 0;
    index = (distant / 20);
    let n = 0;
    detailChangeTimer = setInterval(() => {//模拟“走马灯”轮播图片计时器
        n++;
        let nowWidth = currentMarginLeftWidth - (index * n);//每次“走马灯”计时器需要改变margin-left的大小
        selectorInnerBox.style.marginLeft = nowWidth + "px";//更新每次轮播的margin-left值实现走马灯

        if (n >= 20) {//一次轮播
            currentMarginLeftWidth = newMarginLeftWidth;//重新记录当前margin-left值
            clearInterval(detailChangeTimer);//清除走马灯定时器
            autoChangeTimer = setInterval(autoChangePhoto, timeout);//开启自动轮播计时器
        }
    }, 1)
}


/*鼠标移入选择器后，停止自动轮播*/
for (let i = 0; i < selectorItems.length; i++) {
    selectorItems[i].onmouseover = () => {/*移入停止自动轮播*/
        currentItem = i;
        clearInterval(detailChangeTimer);
        selfChangePhoto();
        clearInterval(autoChangeTimer);
    }

    selectorItems[i].onmouseout = () => {/*移除开启自动轮播*/
        clearInterval(autoChangeTimer);
        clearInterval(detailChangeTimer);
        autoChangeTimer = setInterval(autoChangePhoto, timeout);
    }
}


for (let i = 0; i < selectorPhotos.length; i++) {
    selectorPhotos[i].onmouseover = function () {/*鼠标移入图片，停止自动轮播*/
        clearInterval(autoChangeTimer);
        clearInterval(detailChangeTimer);
    }

    selectorPhotos[i].onmouseout = function () {/*移出开启自动轮播*/
        clearInterval(autoChangeTimer);
        autoChangeTimer = setInterval(autoChangePhoto, timeout);
    }
}

function changeItemColor() {/*被选中的选择项为绿色*/
    for (let i = 0; i < selectorItems.length; i++) {
        selectorItems[i].style.background = "white";
    }
    selectorItems[currentItem].style.background = "green";
}