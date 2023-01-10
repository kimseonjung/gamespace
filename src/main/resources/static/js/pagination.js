// 한페이지에 몇개를 출력할것인가
const rowsPerPage = 10;

const rows = document.querySelectorAll('#news-table .news-lists .news .news-list');
const rowsCount = rows.length; // 201/10 20.1 -> 21
// console.log(rows); 길이찍어보기

//페이지네이션 몇개를 보여줄지
const pageCount = Math.ceil(rowsCount/rowsPerPage);

const numbers = document.querySelector('#numbers');

//이전다음 버튼
const prevBtn = document.querySelector('.pagination .left-icon');
const nextBtn = document.querySelector('.pagination .right-icon');

let pageActiveIdx = 0; // 현재 보고있는 페이지그룹 번호
let currentPageNum = 0; // 현재 보고있는 페이지네이션 번호
let maxPageNum = 10; // 페이지그룹 최대 개수



//페이지네이션 생성
/*
대상.innerHTML = 코드
대상.innerHTML = <li><a href="#">1</a></li>
let c = 대상.innerHTML
for(초기값;조건;증감){

}
* */
for (let i = 1; i <= pageCount; i++){
    // numbers.innerHTML = '<li><a href="">'+i+'</a></li>';
    // numbers.innerHTML = numbers.innerHTML + `<li><a href="">${i}</a></li>`;
    numbers.innerHTML += `<li><a href="">${i}</a></li>`;
}

/* 페이지네이션 업데이트*/
const numberBtn = numbers.querySelectorAll('li');
console.log(numberBtn);

//페이지네이션 번호 감추기
for(nb of numberBtn){
    nb.style.display = 'none';
}




// numberBtn.forEach(function(item,idx){
//
// });
numberBtn.forEach((item,idx)=>{
    item.addEventListener('click',(e)=> {
        e.preventDefault(); //a 태그 기본기능을 막는다 .


        // 테이블 출력 함수
        //console.log(idx);
        displayRow(idx);
    });
});
function displayRow(idx){
    /*
    idx 0
    100개 목록
    인덱스번호 0 ~ 9
    slice(start, end)
    slice(0, 10)
    splice 오려내기

    Nodelist, htmlcollection -> array
    Array.from(대상)
    [...대상]
    */

    // let rowsArray = Array.from(rows);
    // console.log(rowsArray);
    let start = idx*rowsPerPage;
    let end = start+rowsPerPage;

    let rowsArray = [...rows];
    console.log(rowsArray);

    // 리스트 안보이게 하기
    for(ra of rowsArray){
        ra.style.display = 'none';
    }
    let newRows = rowsArray.slice(start, end);
    for(nr of newRows){
        nr.style.display = '';
    }
    for(nb of numberBtn){
        nb.classList.remove('active');
    }
    numberBtn[idx].classList.add('active');

}//displayRow
displayRow(0);

//페이지네이션 그룹표시 함수
function displayPage(num){
    //페이지네이션 번호 감추기
    for(nb of numberBtn){
        nb.style.display = 'none';
    }
    let totalPageCount = Math.ceil(pageCount / maxPageNum);

    let pageArr = [...numberBtn];
    let start = num*maxPageNum;
    let end = start+maxPageNum;
    let pageListArr =pageArr.slice(start, end);

    for(let item of pageListArr){
        item.style.display = 'flex';
    }
    //이전버튼 가리기
    if(pageActiveIdx == 0){
        prevBtn.style.visibility = 'hidden';
    }else{
        prevBtn.style.visibility = 'visible';
    }
    //다음버튼 가리기
    if(pageActiveIdx == totalPageCount - 1){
        nextBtn.style.visibility = 'hidden';
    }else{
        nextBtn.style.visibility = 'visible';
    }


}
displayPage(0);

/* 이전다음 버튼 액션 */
nextBtn.addEventListener('click',()=>{
    let nextPageNum = pageActiveIdx * maxPageNum + maxPageNum;
    displayRow(nextPageNum);
    ++pageActiveIdx;
    displayPage(pageActiveIdx);
})

/*이전*/
prevBtn.addEventListener('click',()=>{
    let nextPageNum = pageActiveIdx * maxPageNum - maxPageNum;
    displayRow(nextPageNum);
    --pageActiveIdx;
    displayPage(pageActiveIdx);
})