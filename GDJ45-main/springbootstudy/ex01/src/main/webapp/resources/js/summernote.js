/**
 * jsp에 포함된 스크립트는 ${contextPath}를 사용할 수 있지만,
 * js에서는 ${contextPath}를 사용할 수 없어서 별도의 함수로 알아냅니다.
 */
function getContextPath(){
	// location 객체의 속성(property)
	// host - localhost:9999
	// href - http://localhost:9999/contextPath/mapping
	var beginIndex = location.href.indexOf(location.host) + location.host.length;
	var endIndex = location.href.indexOf("/", beginIndex + 1);
	var contextPath = location.href.substring(beginIndex, endIndex);
	alert(beginIndex + ', ' + endIndex + ', ' + contextPath);
	return contextPath;
}

function fnSummernote(){
	$('#content').summernote({
		width: 1000,
		height: 600,
		lang: 'ko-KR',
		// 툴바 수정
		// https://summernote.org/deep-dive/#custom-toolbar-popover
		toolbar: [
		    // [groupName, [list of button]]
		    ['fontname', ['fontname']],
		    ['fontsize', ['fontsize']],
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    ['color', ['forecolor','color']],
		    ['table', ['table']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['insert',['picture','link','video']],
		    ['view', ['fullscreen', 'help']]
		],
		fontNames: ['Arial', 'Arial Black', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','36','48','72'],
		callbacks: {
			onImageUpload: function(files) {
				// 에디터에 첨부된 이미지
				var formData = new FormData();
				formData.append('file', files[0]);  // file이 파라미터 이름이 된다. files[0]는 포함된 이미지 파일을 의미한다.
				// 이미지 저장을 위한 ajax
				$.ajax({
					url: getContextPath() + '/bbs/uploadSummernoteImage',  // ${contextPath} 대신 getContextPath() 메소드 호출로 대체
					type: 'POST',
					data: formData,
					contentType: false,
					processData: false,
					dataType: 'json',
					success: function(obj){
						$('#content').summernote('insertImage', obj.src);
						// $('#content').summernote('insertImage', '/getImage/abc.jpg')라고 하면,
						// <img src="/getImage/abc.jpg"> 태그가 만들어진다.
						// /getImage/abc.jpg 요청은 WebMvcConfig에 의해서 C:/upload/summernote/abc.jpg을 읽는 것으로 처리된다.
					}
				})
		    }
		}
	});
}