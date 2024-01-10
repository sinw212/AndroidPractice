# 간단한 TodoApp
할일을 등록 및 수정/삭제하고 Switch를 통해 완료한 할 일을 보관할 수 있는 어플입니다.

MVVM 패턴을 학습하기 위해 간단한 TodoApp을 구현하였습니다.

## ⚙️ 프로젝트 구성
- Stacks

  `MVVM` `AAC ViewModel` `LiveData` `ListAdapter` `Coroutine` `Retrofit`

## 📌 기능 설명
- Todo에 대한 기본적인 CRUD 구현

- Switch 클릭 시, 보관함 추가/삭제 (SharedViewModel을 활용한 Fragment 간 데이터 전달)
    - ViewModel의 LiveData를 observe하여 데이터 변경 감지 시 항목 업데이트되도록 구현

## 📱 구현 화면
![TodoApp](https://github.com/sinw212/KotlinPractice/assets/53486320/bf589f78-e15c-4720-b349-5f2f6618835a)