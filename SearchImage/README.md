# 이미지/비디오 검색 & 보관 어플
키워드를 검색하여 이미지와 비디오를 출력하고 북마크 한 항목이 보관함에 수집되는 어플입니다.

MVVM 패턴 학습을 목적으로, Retrofit 통신을 통해 데이터를 받아오는 앱을 구현하였습니다.

## ⚙️ 프로젝트 구성
- Stacks

  `MVVM` `AAC ViewModel` `LiveData` `ListAdapter` `Coroutine` `Retrofit` `Coil`

## 📌 기능 설명
- 키워드를 검색하여 받아온 이미지와 비디오 최신순 정렬 (Kakao 검색 Open API 활용)
  
- 북마크 아이콘 클릭 시, 보관함 추가/삭제 (SharedViewModel을 활용한 Fragment 간 데이터 전달)
	  - ViewModel의 LiveData를 observe하여 데이터 변경 감지 시 항목 업데이트되도록 구현

## 📱 구현 화면
<img src="https://github.com/sinw212/KotlinPractice/assets/53486320/edb06d55-062e-40a7-8968-aea37ac0cf57" width="420" height="400" />