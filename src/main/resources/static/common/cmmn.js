function gfnCallAddList(url, frm) {
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
                document.getElementById("tbl").append(frag);
            })
        });

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
}

function gfnPageProcess(divn, url, val, valNm) {
    let frm = document.getElementById("defaultFrm");
    let path = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/') + 1);
    frm.setAttribute("action", path + url);

    switch (divn) {
        case 'list' :
            frm.submit();
            break;

        case 'addList' :
            document.getElementById('pageNo').value = val;
            gfnCallAddList(path + url, frm);
            break;

        case 'view' :
            document.getElementById(valNm).value = val;
            frm.submit();
            break;

        case 'insert' :
            frm.submit();
            break;

        case 'update' :
            if(val === undefined) {
                alert("수정되었습니다.");
            } else {
                alert(val);
            }
            frm.submit();
            break;

        case 'submit' :
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