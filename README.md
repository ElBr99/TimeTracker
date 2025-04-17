Многопользовательский тайм-трекер
Задача: разработать Backend сервиса Многопользовательский тайм-трекер, доступ к которому можно получить по REST.

Тайм-трекер (англ. Time-tracker или Time-tracking software) - это категория компьютерного программного обеспечения, которое позволяет сотрудникам, работающим за компьютерами, записывать время, потраченное на выполнение задач или проектов, а работодателям их контролировать.

Типы запросов
создать пользователя трекинга;
изменить данные пользователя;
начать отсчет времени по задаче Х;
прекратить отсчет времени по задаче Х;
показать все трудозатраты пользователя Y за период N..M в виде связного списка Задача - Сумма затраченного времени в виде (чч:мм) с сортировкой от большего к меньшему (для ответа на вопрос, На какие задачи я потратил больше времени);
показать все временные интервалы занятые работой за период N..M в виде связного списка Временной интервал (число чч:мм) - Задача (для ответа на вопросы, На что ушла моя неделя или Где за прошедшую неделю были ‘дыры’, когда я ничего не делал);
показать сумму трудозатрат по всем задачам пользователя Y за период N..M (как будто для отображения на панели Отработано на этой неделе);
очистить данные трекинга пользователя Z;
удалить всю информацию о пользователе Z.

Дополнительные условия
Если пользователь не завершил отсчет своего времени, то он должен завершаться автоматически в 23:59 текущего дня.
Данные должны храниться в системе не более периода, указанного в конфигурации сервиса. Очистка данных производится задачей по расписанию, работающей в составе java приложения.
