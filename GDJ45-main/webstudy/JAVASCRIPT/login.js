// 페이지 로드 이벤트 : HTML문서 모두 읽고 나서 처리할 것.
// 1. onload = function(){}
// 2. $(document).ready(function(){})

$(document).ready(function(){

    // 서브밋 이벤트 처리하기
    var loginForm = $('#login_form');
    loginForm.on('submit', function(event){
        
        // 19시~21시 사이에는 로그인 막기
        var now = new Date();
        if(now.getHours() >= 19 && now.getHours() <= 21){
            alert('시스템 점검중입니다');
            event.preventDefault();  // 현재 이벤트 취소
            return false;  // 이후 코드의 진행을 막는다.
        }

        // 아이디 입력 검사(공백 검사, 정규식 검사)
        if($('#user_id').val() == ''){
            alert('아이디는 필수입니다');
            event.preventDefault();  // 현재 이벤트 취소
            return false;  // 이후 코드의 진행을 막는다.
        }

        // 비밀번호 입력 검사(공백 검사)
        if($('#user_pw').val() == ''){
            alert('비밀번호는 필수입니다');
            event.preventDefault();
            return false;
        }

        // 로그인 허용하는 곳(submit 하는 곳)
        return true;

    });


    // keyup 이벤트 처리하기
    $('#user_id').on('keyup', function(event){
        // this는 이벤트 대상(target)을 의미한다.
        // this == $('#user_id')
        var idCheckResult = $('#id_check_result');
        if( $(this).val().length < 4 ) {
            idCheckResult.text('아이디는 4글자 이상입니다');
            idCheckResult.addClass('no');
            idCheckResult.removeClass('yes');
        } else {
            idCheckResult.text('정상적인 아이디입니다');
            idCheckResult.addClass('yes');
            idCheckResult.removeClass('no');
        }
    });

    // 비밀번호 정규식 체크
    var userPw = $('#user_pw');
    var pwCheckResult = $('#pw_check_result');
    var regPw = /^[0-9a-zA-Z!@#$%]{4,12}$/;
    userPw.on('blur', function(event){
        // 입력된 비밀번호 : $(this).val()
        if(regPw.test($(this).val())==false){
            pwCheckResult.text('비밀번호는 숫자, 대소문자, !@#$%를 사용할 수 있습니다.');
            pwCheckResult.addClass('no');
            pwCheckResult.removeClass('yes');
        } else {
            pwCheckResult.text('사용할 수 있는 비밀번호입니다.');
            pwCheckResult.addClass('yes');
            pwCheckResult.removeClass('no');
        }
    });

});
        