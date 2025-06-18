@echo off
echo off

cd CircPeticionario-WebApp
call go.bat
cd ..

docker network rm network_circ_peticionario

docker network create --subnet=192.168.101.0/24 --gateway=192.168.101.1 network_circ_peticionario

rem podman build -t circ_peticionario_image_app ./FrontEnd
rem podman build -t circ_peticionario_image_payara ./Payara

docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario create

pause