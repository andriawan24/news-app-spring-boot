package id.andriawan.newsapidemo.config;

//@Component
//@RequiredArgsConstructor
//public class ApiKeyAuthFilter extends OncePerRequestFilter {
//    private final ObjectMapper objectMapper;
//
//    @Value("${news.api.key}")
//    private String apiKey;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String requestApiKey = request.getHeader("x-api-key");
//        if (apiKey.equals(requestApiKey) || request.getRequestURI().contains("uploads") || request.getRequestURI().contains("api-docs")  || request.getRequestURI().contains("swagger")) {
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                    "api-user",
//                    null,
//                    Collections.emptyList()
//            );
//            SecurityContextHolder.getContext().setAuthentication(token);
//            filterChain.doFilter(request, response);
//        } else {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            ResponseError errorResponse = new ResponseError("Invalid api key", Instant.now());
//            objectMapper.writeValue(response.getWriter(), errorResponse);
//        }
//    }
//}
