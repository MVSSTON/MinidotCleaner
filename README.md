# Теперь это архивный проект. Последнее обновление VimeWorld добавило возможность отключения персонализаций из самого клиента. Спасибо что пользовались программой!

![banner](https://i.imgur.com/zqKfCU3.png)
### Что это? Зачем нужно?
Если Вам надоели, или просто мешают элементы персонализации на проекте - тогда программа не будет лишней.
### Принцип работы
MinidotCleaner создаёт ресурспак (настраеваемый) который отключает всю персонализацию заменяя её на пустую тексуру (или всю персонализацию, кроме той, что прописана в конфигурационном файле)
### Почему не сделать ресурспак и просто его выложить?
Случаются такие моменты как "обновление проекта", ну это когда ещё персонализацию добавляют иногда. Так вот, постоянно ресурспак перезаливать и переделывать долго и нудно.
Для это и был создан MinidotCleaner, он автоматически возьмёт файлы из архива с игрой и сгенерирует ресурспак на основе предпочтений.
То есть, больше не придётся переделывать ресурспак, скачивать его откуда-то, достаточно запустить программу и она сделаёт всё за Вас.
### Где скачать и как запустить?
Вы можете скачать последний релиз программы в блоке "Releases" на GitHub слева от исходного когда проекта.
Для запуска вы можете прописать команду "java -jar <Имя файла>.jar" или воспользоваться файлом "run.bat" из архива с релизом.
### Пример конфигурации:
```yaml
# Путь до папки .vimeworld
# @user - Переменная автоматически заменяется на имя пользователя
path: 'C:\Users\@user\AppData\Roaming\.vimeworld'

# true - Сохранять ресурспак в папку с ресурспаками автоматически
# false - сохранять ресурспак в этой же папке
inpath: true

# Сервер для которого генерируется ресурспак
# MINIGAMES, EXPLORE, DISCOVER, FLAIR, EMPIRE, WURST, HODEN
# Идея: @CharkosOff <3
direction: MINIGAMES

# Исключения (какие персонализации оставить)
# Названия персонализаций можно посмотреть вызвав программу с параметром --list или запустив файл list.bat
# java -jar MinidotCleaner.jar --list

exclusions:
  - 'assets/minidot/head/turtlehat.png' # Этот пример можно стереть, это шапка черепахи, она есть тут, значит она показывается в игре
# Если вам не нужны исключения и вы хотите отключить всю персонализацию, достаточно
# Стереть код выше и написать просто exclusions: []

# Настройка генерируемого ресурспака
resourcepack:
  output: 'Disabled Minidot' # Название архива ресурспака
  icon: 'default' # Иконка ресурспака (64x64)
  description: '§cРесурс-пак отключающий §lВСЕ\n§cМодели персонализации VimeWorld' # Описание ресурспака
```
