# MED-22 — Lombok: metody generowane przez `@Builder`

## 1. Jak sprawdzić metody wygenerowane przez Lomboka?

Metody wygenerowane przez Lomboka można podejrzeć na dwa sposoby:

- **`javap -p`** — polecenie terminalowe wyświetlające pola, konstruktory i metody znajdujące się w skompilowanym pliku `.class`, również te prywatne.
- **`delombok`** — narzędzie, które przekształca kod z adnotacjami Lomboka w zwykły kod Javy, pokazując wygenerowane elementy.

Przykładowe polecenie dla klasy `PatientBuilder`:

```bash
javap -p 'target/classes/com/github/kaslern/medicalclinic/patient/Patient$PatientBuilder.class'
```

## 2. Metody wygenerowane przez `@Builder` w klasie `Patient`

Poniżej znajduje się wynik pokazujący konstruktor oraz metody wygenerowane dla `PatientBuilder`:

```java
com.github.kaslern.medicalclinic.patient.Patient$PatientBuilder();

public Patient$PatientBuilder email(String email);
public Patient$PatientBuilder password(String password);
public Patient$PatientBuilder idCardNo(String idCardNo);
public Patient$PatientBuilder firstName(String firstName);
public Patient$PatientBuilder lastName(String lastName);
public Patient$PatientBuilder phoneNumber(String phoneNumber);
public Patient$PatientBuilder birthday(LocalDate birthday);

public Patient build();
public String toString();
```


## 3. Do czego służą wygenerowane metody?

- **`Patient.builder()`** — statyczna metoda w klasie `Patient`, która tworzy nowy obiekt `PatientBuilder`.
- **`email()`, `password()`, `firstName()` itd.** — ustawiają odpowiednie wartości w builderze i zwracają ten sam builder, umożliwiając łańcuchowe wywoływanie metod.
- **`build()`** — tworzy obiekt `Patient`, korzystając z konstruktora wskazanego adnotacją `@Builder`.
- **`toString()`** — zwraca tekstową reprezentację buildera.