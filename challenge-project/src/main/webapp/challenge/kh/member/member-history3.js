//템플릿 엔진이 사용할 템플릿 데이터 가져오기
var trTemplateSrc = $("#tr-template2").html();

//위에서 준비한 템플릿 데이터를 가지고 HTML을 생성할 템플릿 엔진 준비
var templateFn = Handlebars.compile(trTemplateSrc);

$.getJSON(serverRoot + "/json/user/list", (data) => {
	//$tableBody.innerHTML = templateFn({list:data});
    $("#listbody2").html(templateFn({list:data}));
});

var trTemplateSrc2 = $("#tr-template").html();

//위에서 준비한 템플릿 데이터를 가지고 HTML을 생성할 템플릿 엔진 준비
var templateFn2 = Handlebars.compile(trTemplateSrc2);

$.getJSON(serverRoot + "/json/community/list", (data) => {
	//$tableBody.innerHTML = templateFn({list:data});
$("#listbody").html(templateFn2({cmu:data}));
});
