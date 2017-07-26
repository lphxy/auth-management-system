/**
 * Created by w1992wishes on 2017/7/26.
 */
function changeFrameHeight(ifm) {
    ifm.height = document.documentElement.clientHeight - 118;
}
function resizeFrameHeight() {
    $('.tab_iframe').css('height', document.documentElement.clientHeight - 118);
    $('md-tab-content').css('left', '0');
}
window.onresize = function(){
    resizeFrameHeight();
}