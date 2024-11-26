# Приложение для управления кошельком

## Описание
Приложение по REST принимает запросы вида:<br>
_POST api/v1/wallet_<br>
 {<br>
   valletId: UUID,<br>
   operationType: DEPOSIT or WITHDRAW,<br>
   amount: 1000<br>
 }<br> 
 <br> 
_GET api/v1/wallets/{WALLET_UUID}_
 <br> 
Приложение должно выполнять логику по изменению счета в ба<br>зе данных,<br>
также есть возможность получить баланс кошелька.<br> 
Стек:
- java 8-17
- Spring 3
- Postgresql<br>
<br> 
Миграции для базы данных реализованы с помощью liquibase.<br> 
Приложение и база данных запускается в докер контейне с помощью docker-compose
