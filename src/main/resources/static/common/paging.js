const PAGE = {
    paging : function(result) {

        //console.log(result);
        /*
        if(total === 0) {
            return false;
        }

        let pageBlock = 10;
        let blockNo = PAGE.toInt(pageNo / pageBlock) + 1;
        let startPageNo = (blockNo - 1) * pageBlock;
        let endPageNo = blockNo * pageBlock - 1;

        if (endPageNo > totalPageCount - 1) {
            endPageNo = totalPageCount - 1;
        }

        let prevBlockPageNo = (blockNo - 1) * pageBlock - 1;
        let nextBlockPageNo = blockNo * pageBlock;
        */
        let html = "";
        let pagingArea = document.getElementById("pagingArea");

        html += '??????????';
        /*
        html += '<ul class="pagination" style="cursor: pointer;">';
        html += '<li class="page-item first">';
        html += '<a class="page-link"><i class="tf-icon bx bx-chevrons-left"></i></a>';
        html += '</li>';
        html += '</ul>';*/

        pagingArea.innerHTML = html;

    },
    toInt : function(value) {
        if(value !== null) {
            return parseInt(value, 10);
        }
    }
}