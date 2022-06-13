import java.util.Scanner;


public class BaseBallGame {

    // 난수 생성 메소드
    public static int[] CpuNum() {
        int[] cpuNum = new int[3];
        // 숫자 생성 로직(cpuNum배열은 [0][1][2]까지 있으므로 cpuNum.length까지 증가 시켜 난수 생성)
        for (int i = 0; i < cpuNum.length; i++) {
            cpuNum[i] = (int) (Math.random() * 9) + 1;

            //중복 제거 로직(j는 i가 1씩 증가할 때 마다 i에 있는 배열의 값과 j에 있는 배열의값을 비교하여 같으면 i를 감소 시켜
            //            중복된 값을 제거 하고 난수를 넣는다.)
            for (int j = 0; j < i; j++) {
                if (cpuNum[i] == cpuNum[j]) {
                    i--;
                }

            }
        }
        // 최종적으로 cpuNum배열값을 반환
        return cpuNum;
    }

    // 입력 받은 값 예외처리하는 메소드
    public static int[] PersonNum() {
        // 사람이 입력할 값을 배열로 생성
        int[] PersonNum = new int[3];

        Scanner scn = new Scanner(System.in);
        // PersonNum.length(3)까지 숫자 입력
        for (int i = 0; i < PersonNum.length; i++) {
            System.out.printf("%d번 째 숫자를 입력하시오.\n", i + 1);
            // 예외처리1 : hasNextInt는 boolean형으로 입력 받은값이 정수인지 판별
            while (!scn.hasNextInt()) {
                scn.next();
                System.out.println("숫자가 아님. 다시 입력");
            }

            int a = scn.nextInt();
            // 예외처리2 : 입력값이 1~9수가 아닌 다른 수를 입력했는지를 판별
            if (a < 0 || a > 9) {
                System.out.println("1부터 9까지의 숫자 중 하나를 입력해주세요. 다시 입력해주세요.");
                i--;

                // 예외처리3 : 입력값이 0인지 판별
            } else if (a == 0) {
                System.out.println("0은 입력하지 마세요. 다시 입력해주세요.");
                i--;
            } else {
                // 예외처리에서 벗어나면 PersonNum배열에 입력값을 저장
                PersonNum[i] = a;
                // 중복 제거 로직(CpuNum에서 쓴 로직과 같음)
                for (int j = 0; j < i; j++) {
                    if (PersonNum[i] == PersonNum[j]) {
                        System.out.println("모두 다른 숫자를 입력해주세요. 다시 입력해주세요.");
                        i--;
                    }
                }
            }
        }
        // 최종적으로 PersonNum배열값을 반환
        return PersonNum;
    }

    // 인게임 메소드 , strike가
    public static int compare(int[] com, int[] user) {
        int strike = 0;
        int ball = 0;

        //  이중 for문으로 cpu의 배열 값이랑 입력받은 값이랑 비교하여 같으면 strike를 증가시키고 다르면 cpu 3개 난수 중에 입력받은 값이 같은 수가 있는지 없는지 비교하고, 있으면
        //  ball을 증가 시킴
        //  비교 순서
        // com[0] != user[0] -> com[0] != user[1] -> com[0] != user[2]
        //   같으면 stirke++        같으면 ball++         같으면 ball++
        // com[1] != user[0] -> com[1] != user[1] -> com[1] != user[2]
        //   같으면 ball++          같으면 stirke++       같으면 ball++
        // com[2] != user[0] -> com[2] != user[1] -> com[2] != user[2]
        //   같으면 ball++          같으면 ball++         같으면 stirke++
        for (int i = 0; i < com.length; i++) {
            if (com[i] == user[i]) {
                strike++;
            } else {
                for (int j = 0; j < user.length; j++) {
                    if (com[i] == user[j]) {
                        ball++;
                    }
                }
            }
        }

        System.out.println("Strike: " + strike + " Ball: " + ball);

        //strike값 반환
        return strike;
    }


    public static void main(String[] args) {

        System.out.println("숫자 야구 게임");

        // a배열에 난수값을 저장
        int[] a = CpuNum();

        int strike = 0;
        int count = 0;

        // strike가 3이면서 시도회수가 10번이 넘으면 while을 빠져나옴
        while (strike != 3 || count < 11) {
            count++;
            System.out.printf("카운트: %d\n", count);

            //숫자 입력받는 메소드 호출하고 반환값 b배열에 저장
            int[] b = PersonNum();

            // compare메소드에서 반환받은 strike값을 strike에 저장
            strike = compare(a, b);

        }

        // 게임이 끝나면 카운트값에 따른 칭찬메세지
       if (count <= 2) {
            System.out.println("참 잘했어요!");
        } else if (count <= 5) {
            System.out.println("잘했어요!");
        } else if (count <= 9) {
            System.out.println("보통이네요!");
        } else {
            System.out.println("분발하세요!");
        }

    }

}


