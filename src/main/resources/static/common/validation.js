/*
 * !!!!!!!!!!!!!!!!!!사용전 필독!!!!!!!!!!!!!!!!!!!!!
 * 유효성 적용 tag에 required, title 속성 필요
*/
let spacePatten = /\s/g;

function formValidation(form) {
    let valChk = 0;

    /* 메세지 중복 쌓임 방지 */
    for(let cls of document.getElementsByClassName('alert_class')) {
        // TODO 태그 삭제방식으로 변경
        // cls.remove();
        cls.innerHTML = '';
    }

    for(let elm of form.elements) {
        if(elm.getAttribute('required') != null) {
            // 입력값 중 사이 공백 검사
            if(elm.type !== 'textarea' && spacePatten.test(elm.value)) {
                validationMsg('space', elm);
                valChk += 1;
            }

            // 필수값 미입력
            if(elm.value.trim() === '') {
                validationMsg('trim', elm);
                valChk += 1;
            }
        }
    }
    return valChk <= 0;
}

function validationMsg(divn, elm) {
    let alert_el = document.createElement('strong');
    alert_el.id = 'alert_' + elm.getAttribute('id');
    alert_el.classList.add('alert_class');
    alert_el.style.color = '#ff3e1d';
    alert_el.style.fontSize = '85%';
    alert_el.style.fontFamily = 'inherit';
    alert_el.style.marginTop = '0.3rem';

    if(divn === 'trim') {
        alert_el.innerText = elm.getAttribute('title') + '은(는) 필수 ' + (elm.type === 'select-one' ? '선택' : '입력') + '입니다.';
    } else {
        alert_el.innerText = elm.getAttribute('title') + ' 값에 공백이 포함되어 있습니다. 다시 입력해주세요.';

    }

    if(elm.parentNode.getAttribute('class') === 'input-group') {
        elm.parentNode.parentNode.appendChild(alert_el);
    } else {
        elm.parentNode.appendChild(alert_el);
    }

}