function gfnCallPage(url, frm, atch) {
    fetch(url, {
        method: "POST",
        /* multipart/form-data로 통신하는 경우 headers 지정하면 오류발생 */
        /* 참고자료
         * https://maivve.tistory.com/298
         * https://boomrabbit.tistory.com/245
         */
        body: new FormData(frm)
    })
        .then(function(res) {
            res.text().then(function(html) {
                let html_dom = new DOMParser().parseFromString(html,'text/html'),
                    frag = document.createDocumentFragment();

                for(let node of html_dom.body.childNodes) {
                    frag.appendChild(node);
                }

                fixScriptsSoTheyAreExecuted(frag);
                document.getElementById(atch).append(frag);
            })
        });
}

/* script 활성화 */
/*
* https://stackoverflow.com/questions/28112807/why-script-elements-created-through-domparser-do-not-execute
*/
function fixScriptsSoTheyAreExecuted(frag) {
    let scripts = frag.querySelectorAll('script'), script, fixedScript, i, len;

    for (i = 0, len = scripts.length; i < len; i++) {
        script = scripts[i];

        fixedScript = document.createElement('script');
        fixedScript.type = script.type;
        if (script.innerHTML) fixedScript.innerHTML = script.innerHTML;
        else fixedScript.src = script.src;
        fixedScript.async = false;

        script.parentNode.replaceChild(fixedScript, script);
    }
}

function gfnPageProcess(divn, url, val, valNm) {
    let frm = document.getElementById("defaultFrm");
    let atchFile = document.getElementById("atchFile");
    let path = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/') + 1);
    frm.setAttribute("action", path + url);

    switch (divn) {
        case 'list' :
            frm.submit();
            break;

        case 'addList' :
            document.getElementById('pageNo').value = val;
            gfnCallPage(path + url, frm, 'tbl');
            break;

        case 'file' :
            gfnCallPage(url, frm, 'fileTbl');
            break;

        case 'view' :
            document.getElementById(valNm).value = val;
            frm.submit();
            break;

        case 'insert' :
            frm.submit();
            break;

        case 'update' :
            if(formValidation(frm)) {
                if (val === undefined) {
                    alert("수정되었습니다.");
                } else {
                    alert(val);
                }
                frm.submit();
            }
            break;

        case 'submit' :
            // 파일처리

            if(formValidation(frm)) {
                if(val === undefined) {
                    alert("등록되었습니다.");
                } else {
                    alert(val);
                }
                frm.submit();
            }
            break;

        case 'delete' :
            if(confirm("삭제하시겠습니까?")) {
                alert("삭제되었습니다.");
                frm.submit();
            } else {
                return false;
            }
            break;
    }
}

function fnFileProcess(fileNm) {
    // 1. 저장 혹은 수정 시 삭제여부와 임시여부가 모두 'Y'인 경우 해당 데이터는 삭제

    // 2. 삭제된 후 등록된 파일들의 임시저장 여부를 모두 'N(정상 저장)' 으로 업데이트

}