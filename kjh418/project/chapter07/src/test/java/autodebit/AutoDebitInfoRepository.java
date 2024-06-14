package autodebit;

// DB 연동을 위한 리포지토리 인터페이스
public interface AutoDebitInfoRepository {
    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}
